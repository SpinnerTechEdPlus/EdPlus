<?php

namespace NotesExamensSeancesBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Note
 *
 * @ORM\Table(name="note")
 * @ORM\Entity(repositoryClass="NotesExamensSeancesBundle\Repository\NoteRepository")
 */
class Note
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
     * @ORM\ManyToOne(targetEntity="\UtilisateursBundle\Entity\User")
     * @ORM\JoinColumn(name="etudiant",referencedColumnName="id")
     */
    private $etudiant;

    /**
     * @var int
     * @ORM\ManyToOne(targetEntity="Examen")
     * @ORM\JoinColumn(name="examen",referencedColumnName="id")
     */
    private $examen;

    /**
     * @return string
     */
    public function getNote()
    {
        return $this->note;
    }

    /**
     * @param string $note
     */
    public function setNote($note)
    {
        $this->note = $note;
    }

    /**
     * @var string
     *
     * @ORM\Column(name="note", type="integer")
     */
    private $note;

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
     * Set etudiant
     *
     * @param integer $etudiant
     *
     * @return Note
     */
    public function setEtudiant($etudiant)
    {
        $this->etudiant = $etudiant;

        return $this;
    }

    /**
     * Get etudiant
     *
     * @return int
     */
    public function getEtudiant()
    {
        return $this->etudiant;
    }

    /**
     * Set examen
     *
     * @param integer $examen
     *
     * @return Note
     */
    public function setExamen($examen)
    {
        $this->examen = $examen;

        return $this;
    }

    /**
     * Get examen
     *
     * @return int
     */
    public function getExamen()
    {
        return $this->examen;
    }
}

