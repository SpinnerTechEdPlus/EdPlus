<?php

namespace GestionNiveauxBundle\Controller;

use FOS\UserBundle\Model\UserInterface;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use GestionNiveauxBundle\Entity\niveau;
use UtilisateursBundle\Entity\User;
use GestionNiveauxBundle\Form\niveauType;
use UtilisateursBundle\Form\UserType;
use UtilisateursBundle\Controller\DefaultController;
use UtilisateursBundle\UtilisateursBundle;


class niveauController extends Controller
{
    public function niveauAction()
    {
        $niveaux = $this->getDoctrine()->getRepository('GestionNiveauxBundle:niveau')->findAll();
        return $this->render('@Utilisateurs/niveau.html.twig', ['niveaux'=>$niveaux ]);
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
}
