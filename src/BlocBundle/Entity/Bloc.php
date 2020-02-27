<?php

namespace BlocBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Bloc
 *
 * @ORM\Table(name="bloc")
 * @ORM\Entity(repositoryClass="BlocBundle\Repository\BlocRepository")
 */
class Bloc
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
    private $nom;

    /**
     * @var int
     *
     * @ORM\Column(name="nbetages", type="integer")
     */
    private $nbetages;


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
     * @return Bloc
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
     * Set nbetages
     *
     * @param integer $nbetages
     *
     * @return Bloc
     */
    public function setNbetages($nbetages)
    {
        $this->nbetages = $nbetages;

        return $this;
    }

    /**
     * Get nbetages
     *
     * @return int
     */
    public function getNbetages()
    {
        return $this->nbetages;
    }
}

