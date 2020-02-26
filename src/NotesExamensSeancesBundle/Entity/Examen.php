<?php

namespace NotesExamensSeancesBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Examen
 *
 * @ORM\Table(name="examen")
 * @ORM\Entity(repositoryClass="NotesExamensSeancesBundle\Repository\ExamenRepository")
 */
class Examen
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
     * @var \DateTime
     *
     * @ORM\Column(name="horaire", type="datetime")
     */
    private $horaire;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="semestre", type="string", length=255)
     */
    private $semestre;

    /**
     * @return \DateTime
     */
    public function getSemestre()
    {
        return $this->semestre;
    }

    /**
     * @param \DateTime $semestre
     */
    public function setSemestre($semestre)
    {
        $this->semestre = $semestre;
    }


    /**
     * @var int
     * @ORM\ManyToOne(targetEntity="Salle")
     * @ORM\JoinColumn(name="salle",referencedColumnName="id")
     */
    private $salle;

    /**
     * @var int
     * @ORM\ManyToOne(targetEntity="Matiere")
     * @ORM\JoinColumn(name="matiere",referencedColumnName="id")
     */
    private $matiere;

    /**
     * @return int
     */
    public function getSalle()
    {
        return $this->salle;
    }

    /**
     * @param int $salle
     */
    public function setSalle($salle)
    {
        $this->salle = $salle;
    }

    /**
     * @return int
     */
    public function getMatiere()
    {
        return $this->matiere;
    }

    /**
     * @param int $matiere
     */
    public function setMatiere($matiere)
    {
        $this->matiere = $matiere;
    }

    /**
     * @return int
     */
    public function getClasse()
    {
        return $this->classe;
    }

    /**
     * @param int $classe
     */
    public function setClasse($classe)
    {
        $this->classe = $classe;
    }


    /**
     * @var int
     * @ORM\ManyToOne(targetEntity="Classe")
     * @ORM\JoinColumn(name="classe",referencedColumnName="id")
     */
    private $classe;



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
     * Set horaire
     *
     * @param \DateTime $horaire
     *
     * @return Examen
     */
    public function setHoraire($horaire)
    {
        $this->horaire = $horaire;

        return $this;
    }

    /**
     * Get horaire
     *
     * @return \DateTime
     */
    public function getHoraire()
    {
        return $this->horaire;
    }
}

