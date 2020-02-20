<?php

namespace SaharBundle\Controller;
use Symfony\Component\HttpFoundation\Request;
use SaharBundle\Entity\Examcorig;
use SaharBundle\Form\ExamcorigType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class ExamcorigController extends Controller
{
    public function AfficheEAction()
    {
        $em = $this->getDoctrine()->getManager();
        $Examcorig = $em->getRepository("SaharBundle:Examcorig")->findAll();
        return $this->render("@Sahar/Examcorig/affiche.html.twig", array('Examcorig' => $Examcorig));
    }

    public function ajoutEAction(Request $request)
    {
        $Examcorig = new Examcorig();

        $form = $this->createForm(ExamcorigType::class, $Examcorig);

        $form->handleRequest($request);
        if ($form->isSubmitted()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($Examcorig);
            $em->flush();
            return $this->redirectToRoute("sahar_affiche_Exam");
        }
        return $this->render("@Sahar/Examcorig/ajout.html.twig", array('form' => $form->createView()));
    }

    public function SupprimerEAction(Request $request, $id)
    {
        $Examcorig = new Examcorig();
        $em = $this->getDoctrine()->getManager();
        $Examcorig = $em->getRepository('SaharBundle:Examcorig')->find($id);
        $em->remove($Examcorig);
        $em->flush();
        return $this->redirectToRoute('sahar_affiche_Exam');
    }

    public function ModifierEAction(Request $request, $id)
    {

        $em = $this->getDoctrine()->getManager();
        $Examcorig = $em->getRepository('SaharBundle:Examcorig')->find($id);
        $form = $this->createForm(ExamcorigType::class, $Examcorig);
        $form->handleRequest($request);

        if ($form->isSubmitted()) {
            $em->persist($Examcorig);
            $em->flush();
            return $this->redirectToRoute('sahar_affiche_Exam');
        }
        return $this->render("@Sahar/Examcorig/modifier.html.twig", array('form' => $form->createView()));
    }
}
