<?php

namespace GestionNiveauxBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * classe
 *
 * @ORM\Table(name="classe")
 * @ORM\Entity(repositoryClass="GestionNiveauxBundle\Repository\classeRepository")
 */
class classe
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
     * @ORM\ManyToOne(targetEntity="niveau", inversedBy="Classes" )
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

    /**
     * @var int
     *
     * @ORM\Column(name="num", type="integer")
     */
    private $num;

    /**
     * @return int
     */
    public function getNom()
    {
        return $this->nom;
    }

    /**
     * @param int $nom
     */
    public function setNom($nom)
    {
        $this->nom = $nom;
    }

    /**
     * @var int
     *
     * @ORM\Column(name="nom", type="string")
     */

    private $nom;


    /**
     * @var int
     *
     * @ORM\Column(name="nbrEtudiant", type="integer")
     */
    private $nbrEtudiant;


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
     * Set num
     *
     * @param integer $num
     *
     * @return \GestionNiveauxBundle\Entity\classe
     */
    public function setNum($num)
    {
        $this->num = $num;

        return $this;
    }

    /**
     * Get num
     *
     * @return int
     */
    public function getNum()
    {
        return $this->num;
    }

    /**
     * Set nbrEtudiant
     *
     * @param integer $nbrEtudiant
     *
     * @return classe
     */
    public function setNbrEtudiant($nbrEtudiant)
    {
        $this->nbrEtudiant = $nbrEtudiant;

        return $this;
    }

    /**
     * Get nbrEtudiant
     *
     * @return int
     */
    public function getNbrEtudiant()
    {
        return $this->nbrEtudiant;
    }
    public  function __toString()
    {
        return(string)$this->getNom();
    }

}
