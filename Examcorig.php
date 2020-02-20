<?php

namespace SaharBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Examcorig
 *
 * @ORM\Table(name="examcorig")
 * @ORM\Entity(repositoryClass="SaharBundle\Repository\ExamcorigRepository")
 */
class Examcorig
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="url_pdf", type="string", length=255)
     */
    private $urlPdf;


    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set urlPdf
     *
     * @param string $urlPdf
     *
     * @return Examcorig
     */
    public function setUrlPdf($urlPdf)
    {
        $this->urlPdf = $urlPdf;

        return $this;
    }

    /**
     * Get urlPdf
     *
     * @return string
     */
    public function getUrlPdf()
    {
        return $this->urlPdf;
    }
}

