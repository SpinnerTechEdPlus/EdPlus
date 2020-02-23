<?php

namespace GestionNiveauxBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * matiere
 *
 * @ORM\Table(name="matiere")
 * @ORM\Entity(repositoryClass="GestionNiveauxBundle\Repository\matiereRepository")
 */
class matiere
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
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }
    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=255)
     */
    private $nom;



    /**
     * Set nom
     *
     * @param string $nom
     *
     * @return Matiere
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
     * @ORM\ManyToOne(targetEntity="niveau", inversedBy="matieres" )
     * @ORM\JoinColumn(name="niveau_id", referencedColumnName="id" )
     */
    private $niveau;

    /**
     * @return mixed
     */
    public function getNiveau()
    {
        return $this->niveau;
    }

    /**
     * @param mixed $niveau
     */
    public function setNiveau($niveau)
    {
        $this->niveau = $niveau;
    }

    public  function __toString()
    {
        return(string)$this->getNom();
    }
}

