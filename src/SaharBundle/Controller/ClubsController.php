<?php

namespace SaharBundle\Controller;
use Symfony\Component\HttpFoundation\Request;
use SaharBundle\Entity\Clubs;
use SaharBundle\Form\ClubsType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Response;

class ClubsController extends Controller
{

    public function AfficheCAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        // $queryBuilder = $this->getClubsRepository()->createFindAllQuery();
        //$queryBuilder = $em->getRepository('SaharBundle:Clubs')->createQueryBuilder('c');

        // if ($request->query->getAlnum('filter')) {
        //$queryBuilder
        //->where('c.nom LIKE :nom')
        // ->setParameter('nom', '%' . $request->query->getAlnum('filter') . '%');
        // }
        // $query= $queryBuilder->getQuery();
        //$Clubs = $em->getRepository("SaharBundle:Clubs")->findAll();
        $dql= "SELECT c FROM SaharBundle:Clubs c";
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
        return $this->render("@Sahar/Clubs/affiche.html.twig", array('Clubs' => $result));
    }

    public function ajoutCAction(Request $request)
    {
        $Clubs = new Clubs();

        $form = $this->createForm(ClubsType::class, $Clubs);

        $form->handleRequest($request);
        //$Clubs->setSujet(sujet: null);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($Clubs);
            $em->flush();
            return $this->redirectToRoute("saharafficheClubs");
        }
        return $this->render("@Sahar/Clubs/ajout.html.twig", array('form' => $form->createView()));
    }

    public function SupprimerCAction(Request $request, $id)
    {
        $Clubs = new Clubs();
        $em = $this->getDoctrine()->getManager();
        $Clubs = $em->getRepository('SaharBundle:Clubs')->find($id);
        $em->remove($Clubs);
        $em->flush();
        return $this->redirectToRoute('saharafficheClubs');
    }

    public function ModifierCAction(Request $request, $id)
    {

        $em = $this->getDoctrine()->getManager();
        $Clubs = $em->getRepository('SaharBundle:Clubs')->find($id);
        $form = $this->createForm(ClubsType::class, $Clubs);
        $form->handleRequest($request);

        if ($form->isSubmitted()&& $form->isValid()) {
            $em->persist($Clubs);
            $em->flush();
            return $this->redirectToRoute('saharafficheClubs');
        }
        return $this->render("@Sahar/Clubs/modifier.html.twig", array('form' => $form->createView()));
    }

    public function searchAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        //$Clubs = $em->getRepository('SaharBundle:Clubs')->findAll();
        $dql = "SELECT c FROM SaharBundle:Clubs c";
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
            $nom = $request->get('nom');
            $result= $em->getRepository("SaharBundle:Clubs")->findBy(array("nom" => $nom));
        }
        return $this->render("@Sahar/Clubs/affiche.html.twig", array('Clubs' => $result));
    }



}