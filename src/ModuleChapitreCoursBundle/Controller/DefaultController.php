<?php

namespace ModuleChapitreCoursBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('ModuleChapitreCoursBundle:Default:index.html.twig');
    }
}
