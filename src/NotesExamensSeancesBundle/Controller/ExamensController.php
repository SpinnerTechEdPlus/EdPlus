<?php

namespace NotesExamensSeancesBundle\Controller;

use NotesExamensSeancesBundle\Entity\Classe;
use NotesExamensSeancesBundle\Entity\Examen;
use NotesExamensSeancesBundle\Entity\Matiere;
use NotesExamensSeancesBundle\Entity\Salle;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class ExamensController extends Controller
{
    public function listAction($id){
        $em = $this->getDoctrine()->getManager();
        $classe = $em->getRepository(\GestionNiveauxBundle\Entity\classe::class)->find($id);

        if(!($classe)){

            return $this->render('@NotesExamensSeances/error.html.twig', array( ));

        }
        else {

            if(isset($_POST['modifier']) && isset($_POST['EidMatiere']) && !empty($_POST['EidMatiere'])  && isset($_POST['EidSalle']) && !empty($_POST['EidSalle'])  && isset($_POST['Esemestre']) && !empty($_POST['Esemestre'])  && isset($_POST['Ehoraire']) && !empty($_POST['Ehoraire']) && isset($_POST['Esemestre']) && !empty($_POST['Esemestre'])
                && isset($_POST['idExam']) && !empty($_POST['idExam'])
            ) {
                $examen = $em->getRepository(Examen::class)->find($_POST['idExam']);
                $nvmatiere = $em->getRepository(\GestionNiveauxBundle\Entity\matiere::class)->find($_POST['EidMatiere']);
                $nvsalle = $em->getRepository(\BlocBundle\Entity\Salle::class)->find($_POST['EidSalle']);
                $examen->setClasse($classe);
                $examen->setHoraire(new \DateTime($_POST['Ehoraire']));
                $examen->setMatiere($nvmatiere);
                $examen->setSalle($nvsalle);
                $examen->setSemestre($_POST['Esemestre']);
                $em = $this->getDoctrine()->getManager();
                $em->persist($examen);
                $em->flush();
                $em = $this->getDoctrine()->getManager();
                $em->flush();

            }



            if(isset($_POST['ajouter']) && isset($_POST['idMatiere']) && !empty($_POST['idMatiere'])  && isset($_POST['idSalle']) && !empty($_POST['idSalle'])  && isset($_POST['semestre']) && !empty($_POST['semestre'])  && isset($_POST['horaire']) && !empty($_POST['horaire']))

            {
                $examen = new Examen();

               $nvmatiere = $em->getRepository(\GestionNiveauxBundle\Entity\matiere::class)->find($_POST['idMatiere']);
               $nvsalle = $em->getRepository(\BlocBundle\Entity\Salle::class)->find($_POST['idSalle']);
                $examen->setClasse($classe);
                $examen->setHoraire(new \DateTime($_POST['horaire']));
                $examen->setMatiere($nvmatiere);
                $examen->setSalle($nvsalle);
                $examen->setSemestre($_POST['semestre']);
                $em = $this->getDoctrine()->getManager();
                $em->persist($examen);
                $em->flush();

            }


            $examens = $em->getRepository(Examen::class)->findBy(array('classe' => $id));
            $matieres = $em->getRepository(\GestionNiveauxBundle\Entity\matiere::class)->findBy(array('niveau' => $classe->getNiveau()->getId()));
            $salles = $em->getRepository(\BlocBundle\Entity\Salle::class)->findAll();









            return $this->render('@NotesExamensSeances/examensecole.html.twig', array('examens' => $examens , 'classe' => $classe , 'matieres' => $matieres , 'salles' => $salles));



        }

    }


    public function deleteAction($id,$examid) {


        $em = $this->getDoctrine()->getManager();
        $examen = $em->getRepository(Examen::class)->find($examid);
        $em->remove($examen);
        $em->flush();
        return $this->redirectToRoute("projets_listpage");

    }
}
