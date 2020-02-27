<?php

namespace BlocBundle\Controller;

use BlocBundle\Entity\Bloc;
use BlocBundle\Entity\Etages;
use BlocBundle\Entity\Salle;
use BlocBundle\Form\BlocType;
use BlocBundle\Form\EtagesType;
use BlocBundle\Form\SalleType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\DependencyInjection\ContainerInterface;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\SearchType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Validator\Constraints\Length;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('@Bloc/Default/index.html.twig');
    }

        public function addBlocAction(Request $request)
        {

            $bloc = new Bloc();
            $form = $this->createForm(BlocType::class, $bloc);
            $form->handleRequest($request);
            if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist( $bloc);
            $em->flush();
            return $this->redirectToRoute('bloc_read');
            }
            return $this->render('@Bloc/Bloc/add.html.twig', [
                'form' => $form->createView()
                                ]);
        }

    public function readbAction(Request $request)
    {

        $bloc=$this->getDoctrine()->getRepository(Bloc::class)->findAll();



        $paginator = $this->get ('knp_paginator');
        $result = $paginator-> paginate (
            $bloc,
        $request-> query-> getInt ('page', 1),
        $request-> query-> getInt ('limit', 1)
        );



        return $this->render('@Bloc/Bloc/readbloc.html.twig', array(
            'bloc'=>$result
        ));
    }

    public function deletebAction($id)
    {

        $bloc = $this->getDoctrine()->getRepository('BlocBundle:Bloc')->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($bloc);
        $em->flush();
        return $this->redirectToRoute('bloc_read');

    }

    public function updatebAction(Request $request,$id)
  {
      $bloc = $this->getDoctrine()->getRepository('BlocBundle:Bloc')->find($id);
      $form = $this->createForm(BlocType::class, $bloc);
      $form->remove('Ajouter');
      $form->add('Modify', SubmitType::class);
      $form->handleRequest($request);
      if ($form->isSubmitted() && $form->isValid()) {

          $bloc = $form->getData();
          $em = $this->getDoctrine()->getManager();
          $em->persist($bloc);
          $em->flush();
          return $this->redirectToRoute('bloc_read');
      }
      return $this->render('@Bloc/Bloc/update.html.twig', array(
          'form' => $form->createView()
      ));
  }


    public function addeAction(Request $request)
    {

        $etages = new Etages();
        $form = $this->createForm(EtagesType::class, $etages);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist( $etages);
            $em->flush();
           return $this->redirectToRoute('etages_read');
        }
        return $this->render('@Bloc/Etages/addetages.html.twig', [
            'form' => $form->createView()
        ]);
    }

    public function readeAction()
    {

        $etages=$this->getDoctrine()->getRepository(etages::class)->findAll();
        return $this->render('@Bloc/Etages/readetages.html.twig', array(
            'etages'=>$etages
        ));
    }



    public function deleteeAction($id)
    {
        $em=$this->getDoctrine()->getManager();
        $etages=$em->getRepository(Etages::class)->find($id);
        $em->remove($etages);
        $em->flush();
        return $this->redirectToRoute('etages_read');
    }


    public function updateeAction(Request $request,$id)
    {
        $etages = $this->getDoctrine()->getRepository('BlocBundle:Etages')->find($id);
        $form = $this->createForm(EtagesType::class, $etages);
        $form->remove('Ajouter');
        $form->add('Modify', SubmitType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {

            $etages = $form->getData();
            $em = $this->getDoctrine()->getManager();
            $em->persist($etages);
            $em->flush();
            return $this->redirectToRoute('etages_read');
        }
        return $this->render('@Bloc/Etages/updateetages.html.twig', array(
            'form' => $form->createView()
        ));
    }



    public function addsAction(Request $request)
    {

        $salle = new Salle();
        $form = $this->createForm(SalleType::class, $salle);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist( $salle);
            $em->flush();
            return $this->redirectToRoute('salle_read');
        }
        return $this->render('@Bloc/salle/addsalle.html.twig', [
            'form' => $form->createView()
        ]);
    }
    public function readsAction()
    {

        $salle=$this->getDoctrine()->getRepository(salle::class)->findAll();
        return $this->render('@Bloc/Salle/readsalle.html.twig', array(
            'salle'=> $salle
        ));
    }


    public function deletesAction($id)
    {
        $em=$this->getDoctrine()->getManager();
        $salle=$em->getRepository(Salle::class)->find($id);
        $em->remove($salle);
        $em->flush();
        return $this->redirectToRoute('salle_read');
    }


    public function updatesAction(Request $request,$id)
    {
        $salle = $this->getDoctrine()->getRepository('BlocBundle:Salle')->find($id);
        $form = $this->createForm(SalleType::class, $salle);
        $form->remove('Ajouter');
        $form->add('Modify', SubmitType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {

            $salle = $form->getData();
            $em = $this->getDoctrine()->getManager();
            $em->persist($salle);
            $em->flush();
            return $this->redirectToRoute('salle_read');
        }
        return $this->render('@Bloc/Salle/updatesalle.html.twig', array(
            'form' => $form->createView()
        ));
    }

    public function bloc_searchAction(Request $request)
    {
        $search = NULL;
        $form = $this->createFormBuilder()
            ->add('type',ChoiceType::class,array(
                'choices' => [
                    'nom' => 'nom',
                    'nbetages' => 'nbetages',
                ],
                'choice_attr' => function() {
                    // adds a class like attending_yes, attending_no, etc
                    return ['class' => 'dropdown-item'];
                },
                'placeholder' => 'Choisir critere de recherche'))

            ->add('search', SearchType::class, array('constraints' => new Length(array('min' => 1)), 'attr' => array('placeholder' => 'Chercher','class'=>'form-control')))
            ->add('send', SubmitType::class, array('label' => 'Chercher','attr'=>array('class'=>'btn btn-success')))
            ->getForm();

        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $search = $form['search']->getData();
            $searchType = $form['type']->getData();
            $em = $this->getDoctrine()->getManager();
            $Blocs = $em->getRepository('BlocBundle:Bloc')->findBy([$searchType=>$search]);
            return $this->render('@Bloc/Bloc/search.html.twig', [
                'Blocs' => $Blocs,
                'form' => $form->createView()
            ]);
        }
        $Blocs = null;
        return $this->render('@Bloc/Bloc/search.html.twig', [
            'Blocs' => $Blocs,
            'form' => $form->createView()
        ]);

    }



}
