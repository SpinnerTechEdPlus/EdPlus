<?php

namespace NotesExamensSeancesBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Seance
 *
 * @ORM\Table(name="seance")
 * @ORM\Entity(repositoryClass="NotesExamensSeancesBundle\Repository\SeanceRepository")
 */
class Seance
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
     * @var int
     * @ORM\ManyToOne(targetEntity="\BlocBundle\Entity\Salle")
     * @ORM\JoinColumn(name="salle",referencedColumnName="id")
     */
    private $salle;

    /**
     * @var int
     * @ORM\ManyToOne(targetEntity="\GestionNiveauxBundle\Entity\matiere")
     * @ORM\JoinColumn(name="matiere",referencedColumnName="id")
     */
    private $matiere;

    /**
     * @var int
     * @ORM\ManyToOne(targetEntity="\UtilisateursBundle\Entity\User")
     * @ORM\JoinColumn(name="professeur",referencedColumnName="id")
     */
    private $professeur;

    /**
     * @var int
     * @ORM\ManyToOne(targetEntity="\GestionNiveauxBundle\Entity\classe")
     * @ORM\JoinColumn(name="classe",referencedColumnName="id")
     */
    private $classe;


    /**
     * @var \DateTime
     *
     * @ORM\Column(name="jour", type="date")
     */
    private $jour;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="debut", type="time")
     */
    private $debut;

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
    public function getProfesseur()
    {
        return $this->professeur;
    }

    /**
     * @param int $professeur
     */
    public function setProfesseur($professeur)
    {
        $this->professeur = $professeur;
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
     * @return \DateTime
     */
    public function getDebut()
    {
        return $this->debut;
    }

    /**
     * @param \DateTime $debut
     */
    public function setDebut($debut)
    {
        $this->debut = $debut;
    }

    /**
     * @return \DateTime
     */
    public function getFin()
    {
        return $this->fin;
    }

    /**
     * @param \DateTime $fin
     */
    public function setFin($fin)
    {
        $this->fin = $fin;
    }



    /**
     * @var \DateTime
     *
     * @ORM\Column(name="fin", type="time")
     */
    private $fin;



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
     * Set jour
     *
     * @param \DateTime $jour
     *
     * @return Seance
     */
    public function setJour($jour)
    {
        $this->jour = $jour;

        return $this;
    }

    /**
     * Get jour
     *
     * @return \DateTime
     */
    public function getJour()
    {
        return $this->jour;
    }
}

