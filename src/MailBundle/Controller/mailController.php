<?php

namespace MailBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use MailBundle\Entity\mail;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use MailBundle\Form\mailType;

class mailController extends Controller
{
    public function sendMailAction(Request $request){
        $mail= new mail();
        $form = $this->createForm(mailType::class, $mail);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $subject = $mail->getSubject();
            $mail = $mail->getMail();
            $object = $request->get('form')['object'];
            $username = 'eyapidev@gmail.com';
            $message = (new \Swift_Message('Hello Email'))
                ->setSubject($subject)
                ->setFrom($username)
                ->setTo($mail)
                ->setBody($object);
            $this->get('mailer')->send($message);
            $this->get('session')->getFlashBag()->add('notice', 'Message Envoyé avec succées');
        }
        return $this->render('@Mail/mail/send_mail.html.twig',array('f'=>$form->createView()));

    }
}
