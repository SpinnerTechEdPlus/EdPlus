<?php

namespace ModuleChapitreCoursBundle\Controller;

use ModuleChapitreCoursBundle\Entity\Cours;
use ModuleChapitreCoursBundle\Form\CoursType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class CoursController extends Controller
{   private $profId = 1 ;
    public function addAction(Request $request)
    {
        $cours = new Cours();
        $form = $this->createForm(CoursType::class, $cours);
        $form->handleRequest($request);
        $em = $this->getDoctrine()->getManager();


        $error = 'noerror';

        if ($form->isSubmitted()  ) {
            $target_dir = "uploads/cours/";
            $target_file = $target_dir . basename($_FILES["pdf"]["name"]);
            $imageFileType = strtolower(pathinfo($target_file,PATHINFO_EXTENSION));
            if ( $imageFileType == 'pdf') {
                $cours->setUpdatedAt(new \DateTime("now"));
                $em->persist($cours);
                $em->flush();
                $lastId = $em->createQueryBuilder()
                    ->select('MAX(e.id)')
                    ->from('ModuleChapitreCoursBundle:Cours', 'e')
                    ->getQuery()
                    ->getSingleScalarResult();
                move_uploaded_file($_FILES["pdf"]["tmp_name"], "cours/".$lastId.".pdf");

                return $this->redirectToRoute('cours_list');
            }
            else $error = 'error';
        }


        return $this->render('@ModuleChapitreCours/addcours.html.twig', array('f' => $form->createView() ,'error' =>$error));

    }

    public function listAction(){
        $em = $this->getDoctrine()->getManager();
        $cours = $em->getRepository(Cours::class)->findAll();
        return $this->render('@ModuleChapitreCours/listcours.html.twig', array('cours' => $cours));

    }

    public function delAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $chapitre = $em->getRepository(Cours::class)->find($id);
        $em->remove($chapitre);
        $em->flush();
        unlink ( "cours/".$id.".pdf");
        return $this->redirectToRoute('cours_list');
    }
}