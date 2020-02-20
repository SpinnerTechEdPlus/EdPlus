<?php

namespace SaharBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Clubs
 *
 * @ORM\Table(name="clubs")
 * @ORM\Entity(repositoryClass="SaharBundle\Repository\ClubsRepository")
 */
class Clubs
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
     * @var string
     *
     * @ORM\Column(name="organization", type="string", length=255)
     */
    private $organization;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="annee_creation", type="date")
     */
    private $anneeCreation;


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
     * @return Clubs
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
     * Set organization
     *
     * @param string $organization
     *
     * @return Clubs
     */
    public function setOrganization($organization)
    {
        $this->organization = $organization;

        return $this;
    }

    /**
     * Get organization
     *
     * @return string
     */
    public function getOrganization()
    {
        return $this->organization;
    }

    /**
     * Set anneeCreation
     *
     * @param \DateTime $anneeCreation
     *
     * @return Clubs
     */
    public function setAnneeCreation($anneeCreation)
    {
        $this->anneeCreation = $anneeCreation;

        return $this;
    }

    /**
     * Get anneeCreation
     *
     * @return \DateTime
     */
    public function getAnneeCreation()
    {
        return $this->anneeCreation;
    }
}

