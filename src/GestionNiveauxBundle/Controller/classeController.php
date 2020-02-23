<?php

namespace GestionNiveauxBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use GestionNiveauxBundle\Entity\classe;
use GestionNiveauxBundle\Form\classeType;

class classeController extends Controller
{
    public function createClasseAction(Request $request)
    {
        $classe = new classe();
        $form = $this->createForm(classeType::class, $classe);

        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $classe = $form->getData();

            $classe->setNom($classe->getNiveau()->getNom()."  ".$classe->getNum());

            $em = $this->getDoctrine()->getManager();

            $em->persist($classe);
            $em->flush();
            return $this->redirectToRoute('niveau_homepage');
        }
        return $this->render('@Utilisateurs/FormViews/createClasse.html.twig', [
            'form'=>$form->createView()]);
    }
    public function classeAction($id)
    {
        $classes = $this->getDoctrine()->getRepository('GestionNiveauxBundle:classe')->findBy(array('niveau'=>$id));
        $em=$this->getDoctrine()->getManager();
        $qb = $em->createQueryBuilder();
       /* foreach ($classes as $classe)
        {
           $id=$classe->getId();

            $etudiants = $this->getDoctrine()->getRepository('UtilisateursBundle:User')->findBy(array('classe'=>$id));

            $nb=count($etudiants);
            $classe->setNbrEtudiant($nb);
            $em->persist($classe);
            $em->flush();

        }*/

        return $this->render('@Utilisateurs/classe.html.twig', ['classes'=>$classes]);

    }
    public function deleteClasseAction($id)
    {
        $em = $this->getDoctrine()->getManager(); //entity manager
        $classe = $this->getDoctrine()->getRepository('GestionNiveauxBundle:classe')->find($id);
        $em->remove($classe);
        $em->flush();

        return $this->redirectToRoute('niveau_homepage');
    }

    public function updateClasseAction($id,Request $request)
    {
        $classe = $this->getDoctrine()->getRepository('GestionNiveauxBundle:classe')->find($id);
        $form = $this->createForm(classeType::class, $classe);
        $form->remove('ajouter');
        $form->add('Modifier',SubmitType::class,['attr' => ['class' => 'btn btn-gradient-primary mr-2'],]);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $classe = $form->getData();
            $classe->setNom($classe->getNiveau()->getNom()."  ".$classe->getNum());

            $em = $this->getDoctrine()->getManager();
            $em->persist($classe);
            $em->flush();
            return $this->redirectToRoute('niveau_homepage');
        }
        return $this->render('@Utilisateurs/FormViews/createClasse.html.twig', [
            'form'=>$form->createView()]);
    }
}
