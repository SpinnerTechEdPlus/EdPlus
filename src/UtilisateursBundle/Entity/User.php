<?php


namespace UtilisateursBundle\Entity;

    use FOS\UserBundle\Model\User as BaseUser;
    use Doctrine\ORM\Mapping as ORM;
    use UtilisateursBundle\Repository\UserRepository;


    /**
     * @ORM\Entity
     * @ORM\Table(name="fos_user")
     */
    class User extends BaseUser
    {
        /**
         * @ORM\Id
         * @ORM\Column(type="integer")
         * @ORM\GeneratedValue(strategy="AUTO")
         */
        protected $id;

        /**
         * @return mixed
         */



        /**
         * @var string
         *
         * @ORM\Column(name="searchName", type="string", length=255)
         */
        public $searchName;

        /**
         * @return mixed
         */
        public function getId()
        {
            return $this->id;
        }

        /**
         * @return string
         */
        public function getSearchName()
        {
            return $this->searchName;
        }

        /**
         * @param string $searchName
         */
        public function setSearchName($searchName)
        {
            $this->searchName = $searchName;
        }

        /**
         * @return string
         */





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
         * @ORM\OneToMany(targetEntity="GestionNiveauxBundle\Entity\niveau", mappedBy="User")
         */

        public $niveau;



        /**
         * @ORM\ManyToOne(targetEntity="GestionNiveauxBundle\Entity\matiere")
         * @ORM\JoinColumn(name="matiere_id", referencedColumnName="id")
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
         * @ORM\ManyToOne(targetEntity="GestionNiveauxBundle\Entity\classe")
         * @ORM\JoinColumn(name="classe_id", referencedColumnName="id")
         */

        public $classe;

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
















































        /**
         * @ORM\ManyToOne(targetEntity="User", inversedBy="Users" )
         * @ORM\JoinColumn(name="user_id", referencedColumnName="id")
         */

        public $User;

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



        public function __construct()
        {
            parent::__construct();
          // $this->User=new ArrayCollection();

        }


    }
