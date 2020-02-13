<?php

namespace ModuleChapitreCoursBundle\Controller;

use ModuleChapitreCoursBundle\Entity\Chapitre;
use ModuleChapitreCoursBundle\Entity\Module;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class ChapitreController extends Controller
{
    public function indexAction($id){

        $em = $this->getDoctrine()->getManager();
        $module = $em->getRepository(Module::class)->find($id);
        if (count($module) != 1) {
            echo "not found";
        } else {


            if (isset($_POST['modifier']) && isset($_POST['mnom']) && !empty($_POST['mnom'])  && isset($_POST['idChapitre']) && !empty($_POST['idChapitre']) ) {
                $chapitre = $em->getRepository(Chapitre::class)->find($_POST['idChapitre']);
                $chapitre->setNom($_POST['mnom']);
                $chapitre->setModule($module);
                $em = $this->getDoctrine()->getManager();
                $em->flush();
            }

            if (isset($_POST['ajouter']) && isset($_POST['nom']) && !empty($_POST['nom']) ) {
                $chapitre = new Chapitre();
                $chapitre->setNom($_POST['nom']);
                $chapitre->setModule($module);
                $em = $this->getDoctrine()->getManager();
                $em->persist($chapitre);
                $em->flush();
            }


            $chapitres = $em->getRepository(Chapitre::class)->findBy(array('module' => $id));

            return $this->render('@ModuleChapitreCours/chapitreecole.html.twig', array('chapitres' => $chapitres , 'module' => $module));
        }

    }

    public function delAction($id,$lastid)
    {
        $em = $this->getDoctrine()->getManager();
        $chapitre = $em->getRepository(Chapitre::class)->find($id);
        $em->remove($chapitre);
        $em->flush();
        return $this->redirectToRoute('module_chapitre_coursf_homepage',array('id'=>$lastid));
    }
}
