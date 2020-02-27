<?php

namespace BlocBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Etages
 *
 * @ORM\Table(name="etages")
 * @ORM\Entity(repositoryClass="BlocBundle\Repository\EtagesRepository")
 */
class Etages
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
     *
     * @ORM\Column(name="numero", type="integer")
     */
    private $numero;

    /**
     * @var int
     *
     * @ORM\Column(name="nbsalle", type="integer")
     */
    private $nbsalle;


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
     * Set numero
     *
     * @param integer $numero
     *
     * @return Etages
     */
    public function setNumero($numero)
    {
        $this->numero = $numero;

        return $this;
    }

    /**
     * Get numero
     *
     * @return int
     */
    public function getNumero()
    {
        return $this->numero;
    }

    /**
     * Set nbsalle
     *
     * @param integer $nbsalle
     *
     * @return Etages
     */
    public function setNbsalle($nbsalle)
    {
        $this->nbsalle = $nbsalle;

        return $this;
    }

    /**
     * Get nbsalle
     *
     * @return int
     */
    public function getNbsalle()
    {
        return $this->nbsalle;
    }
}

