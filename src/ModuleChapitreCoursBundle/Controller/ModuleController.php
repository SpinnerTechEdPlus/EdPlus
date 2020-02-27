<?php

namespace ModuleChapitreCoursBundle\Controller;

use http\Env\Response;
use ModuleChapitreCoursBundle\Entity\Chapitre;
use NotesExamensSeancesBundle\Entity\Matiere;
use ModuleChapitreCoursBundle\Entity\Module;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class ModuleController extends Controller
{
    public function indexAction($id)
    {

        $em = $this->getDoctrine()->getManager();
        $matiere = $em->getRepository(\GestionNiveauxBundle\Entity\matiere::class)->find($id);
        if (!($matiere)) {
            echo "not found";
        } else {


            if (isset($_POST['modifier']) && isset($_POST['mnom']) && !empty($_POST['mnom'])  && isset($_POST['idModule']) && !empty($_POST['idModule']) ) {
                $module = $em->getRepository(Module::class)->find($_POST['idModule']);
                $module->setNom($_POST['mnom']);
                $module->setMatiere($matiere);
                $em = $this->getDoctrine()->getManager();
                $em->flush();
            }

            if (isset($_POST['ajouter']) && isset($_POST['nom'])){
                $module = new Module();
                $module->setNom($_POST['nom']);
                $module->setMatiere($matiere);
                $em = $this->getDoctrine()->getManager();
                $em->persist($module);
                $em->flush();
                $this->redirectToRoute('module_chapitre_cours_homepage',array('id'=>$id));
            }




            $modules = $em->getRepository(Module::class)->findBy(array('matiere' => $id));

            return $this->render('@ModuleChapitreCours/moduleecole.html.twig', array('modules' => $modules, 'matiere' => $matiere));


        }

    }

    public function delAction($id,$lastid)
    {
        $em = $this->getDoctrine()->getManager();
        $module = $em->getRepository(Module::class)->find($id);
        $em->remove($module);
        $em->flush();
        return $this->redirectToRoute('module_chapitre_cours_homepage',array('id'=>$lastid));
    }

    public function statAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $modules = $em->getRepository(Module::class)->findBy(array('matiere' => $id));
        $nbr = [] ;
        $matiere = null ;
        foreach ( $modules as $module) {
            array_push($nbr,count($em->getRepository(Chapitre::class)->findBy(array('module' => $module->getId()))));
            $matiere = $module->getMatiere();
        }
        return $this->render('@ModuleChapitreCours/modulestat.html.twig', array('modules' => $modules , 'nbr' => $nbr ,'matiere' => $matiere));
    }
}

