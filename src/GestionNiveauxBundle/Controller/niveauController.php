<?php

namespace GestionNiveauxBundle\Controller;

use FOS\UserBundle\Model\UserInterface;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use GestionNiveauxBundle\Entity\niveau;
use Symfony\Component\HttpFoundation\Response;

use UtilisateursBundle\Entity\User;
use GestionNiveauxBundle\Form\niveauType;
use UtilisateursBundle\Form\UserType;
use UtilisateursBundle\Controller\DefaultController;
use UtilisateursBundle\UtilisateursBundle;


class niveauController extends Controller
{
    public function niveauAction(Request $request)
    {
        $niveaux = $this->getDoctrine()->getRepository('GestionNiveauxBundle:niveau')->findAll();
        $dql= "SELECT n FROM GestionNiveauxBundle:niveau n";
        $em= $this->getDoctrine()->getManager();

        $query=$em->createQuery($dql);

        $qb=$em->createQuery($niveaux);
        /**
         * @var $paginator \Knp\Component\Pager\Paginator
         */
        $paginator=$this->get('knp_paginator');
        $result= $paginator->paginate(
            $query,
            $request->query->getInt('page',1),
            $request->query->getInt('limit',3)



        );

        return $this->render('@Utilisateurs/niveau.html.twig', ['niveaux'=>$result ]);
    }
    public function deleteNiveauAction($id)
    {
        $em = $this->getDoctrine()->getManager(); //entity manager
        $niveau = $this->getDoctrine()->getRepository('GestionNiveauxBundle:niveau')->find($id);
        $em->remove($niveau);
        $em->flush();
        return $this->redirectToRoute('niveau_homepage');
    }

    public function createNiveauAction(Request $request)
    {
        $niveau = new niveau();
        $user=$this->getUser();
        $niveau->setUser($user);

        $form = $this->createForm(niveauType::class, $niveau);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $niveau = $form->getData();
            $em = $this->getDoctrine()->getManager();
            $em->persist($niveau);
            $em->flush();
            return $this->redirectToRoute('niveau_homepage');
        }
        return $this->render('@Utilisateurs/FormViews/createNiveau.html.twig', [
            'form'=>$form->createView()]);
    }
   public function updateNiveauAction($id,Request $request)
    {
        $niveau = $this->getDoctrine()->getRepository('GestionNiveauxBundle:niveau')->find($id);
        $form = $this->createForm(niveauType::class, $niveau);
        $form->remove('ajouter');
        $form->add('Modifier',SubmitType::class,['attr' => ['class' => 'btn btn-gradient-primary mr-2'],]);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $niveau = $form->getData();
            $em = $this->getDoctrine()->getManager();
            $em->persist($niveau);
            $em->flush();
            return $this->redirectToRoute('niveau_homepage');
        }
        return $this->render('@Utilisateurs/FormViews/createNiveau.html.twig', [
            'form'=>$form->createView()]);

    }


    public function searchNiveauAction(Request $request)
    {  $requestString=$request->get('q') ;


        $em = $this->getDoctrine()->getManager();

        $qb = $em->createQueryBuilder();
        if($requestString!=null) {


            $qb->select('n')
                ->from('GestionNiveauxBundle:niveau', 'n') // Change this to the name of your bundle and the name of your mapped user Entity
                ->where('n.nom = :str')


                ->setParameter('str', $requestString);
        }


        $niveaux = $qb->getQuery()->getResult();

        if(!$niveaux)
        {
            $result['niveaux']['error']="niveau not found";
        }
        else{
            $result['niveaux']=$this->getNiveauEntities($niveaux);
        }

        return new Response(json_encode($result));


    }

    public function getNiveauEntities($niveaux)
    {
        foreach ($niveaux as $niveaux)
        {
            $realEntities[$niveaux->getId()] =[$niveaux->getId(),$niveaux->getNom()];
        }
        return $realEntities;
    }
}
