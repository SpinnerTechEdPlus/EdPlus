<?php

namespace ModuleChapitreCoursBundle\Entity;

use DateTime;
use Doctrine\ORM\Mapping as ORM;

use Vich\UploaderBundle\Mapping\Annotation as Vich;

/**
 * Cours
 *
 * @ORM\Table(name="cours")
 * @ORM\Entity(repositoryClass="ModuleChapitreCoursBundle\Repository\CoursRepository")
 * @Vich\Uploadable
 */
class Cours
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
     * @ORM\Column(name="description", type="string", length=255)
     */
    private $description;

    /**
     * @var int
     * @ORM\ManyToOne(targetEntity="Professeur")
     * @ORM\JoinColumn(name="professeur",referencedColumnName="id")
     */
    private $professeur;


    /**
     * @var int
     * @ORM\ManyToOne(targetEntity="Chapitre")
     * @ORM\JoinColumn(name="chapitre",referencedColumnName="id")
     */
    private $chapitre;

    /**
     * @Vich\UploadableField(mapping="cours", fileNameProperty="coursName")
     *
     * @var File
     */
    private $coursFile;

    /**
     * @return string
     */
    public function getCoursName()
    {
        return $this->coursName;
    }

    /**
     * @param string $coursName
     * @return Cours
     */
    public function setCoursName($coursName)
    {
        $this->coursName = $coursName;

        return $this;
    }

    /**
     * @return DateTime
     */
    public function getUpdatedAt()
    {
        return $this->updatedAt;
    }

    /**
     * @param DateTime $updatedAt
     */
    public function setUpdatedAt($updatedAt)
    {
        $this->updatedAt = $updatedAt;
    }
    /**
     * @ORM\Column(type="string", length=255)
     *
     * @var string
     */
    private $coursName;

    /**
     * @ORM\Column(type="datetime")
     *
     * @var DateTime
     */
    private $updatedAt;

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
     * Set description
     *
     * @param string $description
     *
     * @return Cours
     */
    public function setDescription($description)
    {
        $this->description = $description;

        return $this;
    }

    /**
     * Get description
     *
     * @return string
     */
    public function getDescription()
    {
        return $this->description;
    }

    /**
     * Set professeur
     *
     * @param string $professeur
     *
     * @return Cours
     */
    public function setProfesseur($professeur)
    {
        $this->professeur = $professeur;

        return $this;
    }

    /**
     * Get professeur
     *
     * @return string
     */
    public function getProfesseur()
    {
        return $this->professeur;
    }

    /**
     * Set chapitre
     *
     * @param string $chapitre
     *
     * @return Cours
     */
    public function setChapitre($chapitre)
    {
        $this->chapitre = $chapitre;

        return $this;
    }

    /**
     * Get chapitre
     *
     * @return string
     */
    public function getChapitre()
    {
        return $this->chapitre;
    }

    /**
     * @param File|\Symfony\Component\HttpFoundation\File\UploadedFile $cours
     *
     * @return Cours
     * @throws \Exception
     */
    public function setCoursFile($cours = null)
    {
        $this->file = $cours;
         if($cours) {

             $this->updatedAt = new DateTime('now');

         }
        return $this;
    }

    /**
     * @return File|null
     */
    public function getCoursFile()
    {
        return $this->coursFile;
    }
}

