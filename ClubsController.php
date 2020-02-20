<?php

namespace SaharBundle\Controller;
use Symfony\Component\HttpFoundation\Request;
use SaharBundle\Entity\Clubs;
use SaharBundle\Form\ClubsType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class ClubsController extends Controller
{
    public function AfficheCAction()
    {
        $em = $this->getDoctrine()->getManager();
        $Clubs = $em->getRepository("SaharBundle:Clubs")->findAll();
        return $this->render("@Sahar/Clubs/affiche.html.twig", array('Clubs' => $Clubs));
    }

    public function ajoutCAction(Request $request)
    {
        $Clubs = new Clubs();

        $form = $this->createForm(ClubsType::class, $Clubs);

        $form->handleRequest($request);
        //$Clubs->setSujet(sujet: null);
        if ($form->isSubmitted()) {
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

        if ($form->isSubmitted()) {
            $em->persist($Clubs);
            $em->flush();
            return $this->redirectToRoute('saharafficheClubs');
        }
        return $this->render("@Sahar/Clubs/modifier.html.twig", array('form' => $form->createView()));
    }

    public function searchAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $requestString = $request->get('q');
        $Clubs =  $em->getRepository('SaharBundle:Clubs')->findEntitiesByString($requestString);
        if(!$Clubs) {
            $result['Clubs']['error'] = "Post Not found :( ";
        } else {
            $result['Clubs'] = $this->getRealEntities($Clubs);

        }
        return new Response(json_encode($result));
    }

    public function getRealEntities($Clubs)
    {
        foreach ($Clubs as $Clubs){
            $realEntities[$Clubs->getId()] = [$Clubs->getnom(),$Clubs->getorganization(),$Clubs->getanneeCreation()];

        }
        return $realEntities;

    }
}