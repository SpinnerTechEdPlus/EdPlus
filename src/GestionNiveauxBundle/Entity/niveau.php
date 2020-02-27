<?php

namespace GestionNiveauxBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * niveau
 *
 * @ORM\Table(name="niveau")
 * @ORM\Entity(repositoryClass="GestionNiveauxBundle\Repository\niveauRepository")
 */
class niveau
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
     * @ORM\ManyToOne(targetEntity="UtilisateursBundle\Entity\User", inversedBy="Niveaux" )
     * @ORM\JoinColumn(name="user_id", referencedColumnName="id")
     */
    private $User;

    /**
     * @return mixed
     */
    public function getClasse()
    {
        return $this->classe;
    }

    /**
     * @param mixed $classe
     */
    public function setClasse($classe)
    {
        $this->classe = $classe;
    }

    public function setId($classe)
    {
        $this->id = $classe;
    }


    /**
     * @ORM\OneToMany(targetEntity="classe", mappedBy="niveau" )
     */

    public $classe;

    /**
     * @ORM\OneToMany(targetEntity="matiere", mappedBy="niveau" )
     */

    public $matiere;

    /**
     * @return mixed
     */
    public function getMatiere()
    {
        return $this->matiere;
    }

    /**
     * @param mixed $matiere
     */
    public function setMatiere($matiere)
    {
        $this->matiere = $matiere;
    }
    /**
     * @return mixed
     */
    public function getUser()
    {
        return $this->User;
    }

    /**
     * @param mixed $User
     */
    public function setUser($User)
    {
        $this->User = $User;
    }


    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=255)
     */
    private $nom;

    /**
     * @var int
     *
     * @ORM\Column(name="nivScolaire", type="integer")
     */
    private $nivScolaire;


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
     * Set nom
     *
     * @param string $nom
     *
     * @return niveau
     */
    public function setNom($nom)
    {
        $this->nom = $nom;

        return $this;
    }

    /**
     * Get nom
     *
     * @return string
     */
    public function getNom()
    {
        return $this->nom;
    }

    /**
     * Set nivScolaire
     *
     * @param integer $nivScolaire
     *
     * @return niveau
     */
    public function setNivScolaire($nivScolaire)
    {
        $this->nivScolaire = $nivScolaire;

        return $this;
    }

    /**
     * Get nivScolaire
     *
     * @return int
     */
    public function getNivScolaire()
    {
        return $this->nivScolaire;
    }

    public  function __toString()
    {
        return(string)$this->getNom();
    }
}

