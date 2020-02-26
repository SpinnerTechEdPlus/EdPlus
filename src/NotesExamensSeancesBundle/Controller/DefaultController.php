<?php

namespace NotesExamensSeancesBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('NotesExamensSeancesBundle:Default:index.html.twig');
    }
}
