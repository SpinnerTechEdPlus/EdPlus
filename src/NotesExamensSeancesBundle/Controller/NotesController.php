<?php

namespace NotesExamensSeancesBundle\Controller;

use NotesExamensSeancesBundle\Entity\Etudiant;
use NotesExamensSeancesBundle\Entity\Examen;
use NotesExamensSeancesBundle\Entity\Note;
use NotesExamensSeancesBundle\Entity\Osms;
use NotesExamensSeancesBundle\Entity\Seance;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class NotesController extends Controller
{

    public function ClassesListAction(){

        $profId = 1 ;

        $em = $this->getDoctrine()->getManager();
        $examens = $em->getRepository(Examen::class)->findAll();


        $seances = $em->getRepository(Seance::class)->findBy(array('professeur' => $profId));




        return $this->render('@NotesExamensSeances/noteclasses.html.twig', array('examens' => $examens ,'seances' => $seances , 'profId' => $profId));



        }



    public function NotesListAction($id){



        $em = $this->getDoctrine()->getManager();
        $examen = $em->getRepository(Examen::class)->find($id);


        $etudiants = $em->getRepository(Etudiant::class)->findBy(array('classe' => $examen->getClasse()));

        $notes = $em->getRepository(Note::class)->findBy(array('examen' => $id));



        return $this->render('@NotesExamensSeances/notes.html.twig', array('examen' => $examen ,'etudiants' => $etudiants ,'notes' => $notes));



    }

    public function addNoteAction($examen,$etudiant,$note)
    {
        $notee = new Note();

        $em = $this->getDoctrine()->getManager();
        $nvexamen = $em->getRepository(Examen::class)->find($examen);
        $nvetudiant = $em->getRepository(Etudiant::class)->find($etudiant);
        $notee->setNote($note);
        $notee->setEtudiant($nvetudiant);
        $notee->setExamen($nvexamen);
        $em = $this->getDoctrine()->getManager();
        $em->persist($notee);
        $em->flush();
        $nt = $em->getRepository(Note::class)->findOneBy(array('examen' => $examen , 'etudiant' => $etudiant));

        $transport = \Swift_SmtpTransport::newInstance()
            ->setUsername('edplustn@outlook.com')->setPassword('ed10203030')
            ->setHost('smtp-mail.outlook.com')
            ->setPort(587)->setEncryption('tls');

        $mailer = \Swift_Mailer::newInstance($transport);

        $message = \Swift_Message::newInstance()
            ->setSubject('ED PLUS | Nouvelle Note Ajouté')
            ->setFrom(array('edplustn@outlook.com' => 'ED +'))
            ->setTo(array($nvetudiant->getMail() => $nvetudiant->getMail()))
            ->addPart("<h1>Ed+ | VOTRE NOTE DE  L'EXAMEN ". $nvexamen->getMatiere()->getNom()." EST PUBLIÉ  - VOTRE NOTE :  ".$note."</h1>",'text/html')
        ;

        $result = $mailer->send($message);

        if($nvetudiant->getTel()!=0){

            $config = array(
                'clientId' => 'ALqAskn3ploNZH9V0Sxf5h00QKxekXil',
                'clientSecret' => 'NzcGAByB610EDFsJ'
            );

            $osms = new Osms($config);
            $osms->setVerifyPeerSSL(false);
// retrieve an access token
            $response = $osms->getTokenFromConsumerKey();


            if (!empty($response['access_token'])) {
                $telephone=$nvetudiant->getTel();
                $senderAddress = 'tel:+21656479504';
                $receiverAddress = 'tel:+216'.$nvetudiant->getTel();


                $senderName = 'Ed+';
                $messageemail='Ed+ | VOTRE NOTE DE  L\'EXAMEN '. $nvexamen->getMatiere()->getNom() .' EST PUBLIÉ.'."\n".'VOTRE NOTE :  '.$note;

                 $osms->sendSMS($senderAddress, $receiverAddress, $messageemail, $senderName);

            } else {

            }
        }

        return $this->render('@NotesExamensSeances/notesajax.html.twig', array('id' => $nt->getId()  ));
    }


    public function editNoteAction($noteid,$note)
    {

        $em = $this->getDoctrine()->getManager();
        $notee =  $em->getRepository(Note::class)->find($noteid);


        $notee->setNote($note);

        $em = $this->getDoctrine()->getManager();
        $em->flush();

        if($notee->getEtudiant()->getTel()!=0){

            $config = array(
                'clientId' => 'ALqAskn3ploNZH9V0Sxf5h00QKxekXil',
                'clientSecret' => 'NzcGAByB610EDFsJ'
            );

            $osms = new Osms($config);
            $osms->setVerifyPeerSSL(false);
// retrieve an access token
            $response = $osms->getTokenFromConsumerKey();


            if (!empty($response['access_token'])) {
                $telephone=$notee->getEtudiant()->getTel();
                $senderAddress = 'tel:+21656479504';
                $receiverAddress = 'tel:+216'.$notee->getEtudiant()->getTel();


                $senderName = 'Ed+';
                $messageemail='Ed+ | VOTRE NOTE DE L\'EXAMEN '. $notee->getExamen()->getMatiere()->getNom() .' EST MODIFIÉ.'."\n".'VOTRE NOUVELLE NOTE :  '.$note;

                $osms->sendSMS($senderAddress, $receiverAddress, $messageemail, $senderName);

            } else {

            }
        }


        return $this->render('@NotesExamensSeances/notesajax.html.twig', array('id' => ''  ));

    }


    public function deleteNoteAction($noteid)
    {

        $em = $this->getDoctrine()->getManager();
        $note = $em->getRepository(Note::class)->find($noteid);
        $em->remove($note);
        $em->flush();
        return $this->render('@NotesExamensSeances/notesajax.html.twig', array('id' => ''  ));

    }



    }
