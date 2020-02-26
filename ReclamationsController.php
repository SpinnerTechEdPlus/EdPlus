<?php

namespace SaharBundle\Controller;
use Symfony\Component\HttpFoundation\Request;
use SaharBundle\Entity\Reclamations;
use SaharBundle\Form\ReclamationsType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class ReclamationsController extends Controller
{
    public function AfficheRAction(Request $request)
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
    $dql= "SELECT r FROM SaharBundle:Reclamations r";
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
    return $this->render("@Sahar/Reclamations/affiche.html.twig", array('Reclamations' => $result));
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

        if ($form->isSubmitted()&& $form->isValid()) {
            $em->persist($Reclamations);
            $em->flush();
            return $this->redirectToRoute('sahar_affiche_Rec');
        }
        return $this->render("@Sahar/Reclamations/modifier.html.twig", array('form' => $form->createView()));
    }
    public function searchAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
      //  $Reclamations = $em->getRepository('SaharBundle:Reclamations')->findAll();
        $dql = "SELECT r FROM SaharBundle:Reclamations r";
        $query =$em->createQuery($dql);
        /**
         * @var $paginator \Knp\Component\Pager\Paginator
         */
        $paginator = $this->get('knp_paginator');
        $result = $paginator->paginate(
            $query,
            $request->query->getInt('page', 1),
            $request->query->getInt('limit',5)
        );
        if ($request->isMethod('POST')) {
            $titre = $request->get('titre');
            $result= $em->getRepository("SaharBundle:Reclamations")->findBy(array("titre" => $titre));
        }
        return $this->render("@Sahar/Reclamations/affiche.html.twig", array('Reclamations' => $result));
    }
}
