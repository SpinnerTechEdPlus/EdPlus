<?php

namespace SaharBundle\Controller;
use Symfony\Component\HttpFoundation\Request;
use SaharBundle\Entity\Examcorig;
use SaharBundle\Form\ExamcorigType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\File\File;

class ExamcorigController extends Controller
{
    public function AfficheEAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        // $queryBuilder = $this->getReclamationsRepository()->createFindAllQuery();
        //$queryBuilder = $em->getRepository('SaharBundle:Reclamations')->createQueryBuilder('c');

        // if ($request->query->getAlnum('filter')) {
        //$queryBuilder
        //->where('c.nom LIKE :nom')
        // ->setParameter('nom', '%' . $request->query->getAlnum('filter') . '%');
        // }
        // $query= $queryBuilder->getQuery();
        //$Reclamations = $em->getRepository("SaharBundle:Reclamations")->findAll();
        $dql= "SELECT e FROM SaharBundle:Examcorig e";
        $query=$em->createQuery($dql);
        /**
         * @var $paginator \Knp\Component\Pager\Paginator
         */
        $paginator = $this->get('knp_paginator');
        $result = $paginator->paginate(
            $query,
            $request->query->getInt('page', 1),
            $request->query->getInt('limit',5)
        );
        return $this->render("@Sahar/Examcorig/affiche.html.twig", array('Examcorig' => $result));

    }

    public function ajoutEAction(Request $request)
    {
        $Examcorig = new Examcorig();

        $form = $this->createForm(ExamcorigType::class, $Examcorig);

        $form->handleRequest($request);
        if ($form->isSubmitted()&& $form->isValid()) {
            $em = $this->getDoctrine()->getManager();

            $file = $Examcorig-> getUrlPdf();
            $fileName= md5(uniqid()).'.'.$file->guessExtension();
            $file->move(
                $this->getParameter('brochures_directory'),
                $fileName
            );
            $Examcorig->setUrlPdf($fileName);

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

        if ($form->isSubmitted()&& $form->isValid()) {
            $em->persist($Examcorig);
            $em->flush();
            return $this->redirectToRoute('sahar_affiche_Exam');
        }
        return $this->render("@Sahar/Examcorig/modifier.html.twig", array('form' => $form->createView()));
    }
    public function searchAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $Examcorig = $em->getRepository('SaharBundle:Examcorig')->findAll();
        if ($request->isMethod('POST')) {
            $urlPdf = $request->get('urlPdf');
            $Examcorig = $em->getRepository("SaharBundle:Examcorig")->findBy(array("urlPdf" => $urlPdf));
        }
        return $this->render("@Sahar/Examcorig/affiche.html.twig", array('Examcorig' => $Examcorig));
    }
}
