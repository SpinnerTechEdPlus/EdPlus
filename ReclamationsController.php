<?php

namespace SaharBundle\Controller;
use Symfony\Component\HttpFoundation\Request;
use SaharBundle\Entity\Reclamations;
use SaharBundle\Form\ReclamationsType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class ReclamationsController extends Controller
{
    public function AfficheRAction()
{
    $em = $this->getDoctrine()->getManager();
    $Reclamations = $em->getRepository("SaharBundle:Reclamations")->findAll();
    return $this->render("@Sahar/Reclamations/affiche.html.twig", array('Reclamations' => $Reclamations));
}

    public function ajoutRAction(Request $request)
    {
        $Reclamations = new Reclamations();

        $form = $this->createForm(ReclamationsType::class, $Reclamations);

        $form->handleRequest($request);
        if ($form->isSubmitted()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($Reclamations);
            $em->flush();
            return $this->redirectToRoute("sahar_affiche_Rec");
        }
        return $this->render("@Sahar/Reclamations/ajout.html.twig", array('form' => $form->createView()));
    }

    public function SupprimerRAction(Request $request, $id)
    {
        $Reclamations = new Reclamations();
        $em = $this->getDoctrine()->getManager();
        $Reclamations = $em->getRepository('SaharBundle:Reclamations')->find($id);
        $em->remove($Reclamations);
        $em->flush();
        return $this->redirectToRoute('sahar_affiche_Rec');
    }

    public function ModifierRAction(Request $request, $id)
    {

        $em = $this->getDoctrine()->getManager();
        $Reclamations = $em->getRepository('SaharBundle:Reclamations')->find($id);
        $form = $this->createForm(ReclamationsType::class, $Reclamations);
        $form->handleRequest($request);

        if ($form->isSubmitted()) {
            $em->persist($Reclamations);
            $em->flush();
            return $this->redirectToRoute('sahar_affiche_Rec');
        }
        return $this->render("@Sahar/Reclamations/modifier.html.twig", array('form' => $form->createView()));
    }
}
