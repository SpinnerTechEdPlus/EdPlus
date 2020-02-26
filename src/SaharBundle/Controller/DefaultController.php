<?php

namespace SaharBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('SaharBundle:Default:index.html.twig');
    }
}
