<?php

namespace SaharBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Form\Extension\Core\Type\FileType;
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
     * @ORM\Column(name="nom", type="string", length=255)
     */
    private $matiere;

    /**
     * @return string
     */
    public function getMatiere()
    {
        return $this->matiere;
    }

    /**
     * @param string $matiere
     */
    public function setMatiere($matiere)
    {
        $this->matiere = $matiere;
    }

    /**
     * @ORM\Column(type="string")
     *
     * @Assert\NotBlank(message="Please, upload the product brochure as a PDF file.")
     * @Assert\File(mimeTypes={ "application/pdf" })
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

