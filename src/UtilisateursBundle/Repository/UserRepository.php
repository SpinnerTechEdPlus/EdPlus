<?php

namespace UtilisateursBundle\Repository;

use UtilisateursBundle\Entity\User;
use Doctrine\ORM\Query;

class UserRepository extends \Doctrine\ORM\EntityRepository
{

    public function findByString($str)
    {
        return $this->getEntityManager()
            ->createQuery(
                'SELECT u
                FROM UtilisateursBundle:User u
                WHERE u.Username LIKE :str'
            )
            ->setParameter('str','%'.$str.'%')
            ->getResult();
    }
}
