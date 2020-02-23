<?php

namespace GestionNiveauxBundle\Controller;

use GestionNiveauxBundle\Entity\matiere;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use GestionNiveauxBundle\Form\matiereType;

class matiereController extends Controller
{
    public function createMatiereAction(Request $request)
{
    $matiere = new matiere();
    $form = $this->createForm(matiereType::class, $matiere);
    $form->handleRequest($request);
    if ($form->isSubmitted() && $form->isValid()) {
        $matiere = $form->getData();
        $em = $this->getDoctrine()->getManager();
        $em->persist($matiere);
        $em->flush();
        return $this->redirectToRoute('niveau_homepage');
    }
    return $this->render('@Utilisateurs/FormViews/createMatiere.html.twig', [
        'form'=>$form->createView()]);
}
    public function matiereAction($id)
    {
        $matieres = $this->getDoctrine()->getRepository('GestionNiveauxBundle:matiere')->findBy(array('niveau'=>$id));
        return $this->render('@Utilisateurs/matiere.html.twig', ['matieres'=>$matieres]);

    }
    public function deleteMatiereAction($id)
    {
        $em = $this->getDoctrine()->getManager(); //entity manager
        $matiere = $this->getDoctrine()->getRepository('GestionNiveauxBundle:matiere')->find($id);
        $em->remove($matiere);
        $em->flush();

        return $this->redirectToRoute('niveau_homepage');
    }

    public function updateMatiereAction($id,Request $request)
    {
        $matiere = $this->getDoctrine()->getRepository('GestionNiveauxBundle:matiere')->find($id);
        $form = $this->createForm(matiereType::class, $matiere);
        $form->remove('ajouter');
        $form->add('Modifier',SubmitType::class,['attr' => ['class' => 'btn btn-gradient-primary mr-2'],]);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $matiere = $form->getData();
            $em = $this->getDoctrine()->getManager();
            $em->persist($matiere);
            $em->flush();
            return $this->redirectToRoute('niveau_homepage');
        }
        return $this->render('@Utilisateurs/FormViews/createMatiere.html.twig', [
            'form'=>$form->createView()]);
    }
}
