<?php

namespace UtilisateursBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\EmailType;
use Symfony\Component\Form\Extension\Core\Type\PasswordType;
use Symfony\Component\Form\Extension\Core\Type\RepeatedType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class UserType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('email', EmailType::class, array('label' => 'email', 'translation_domain' => 'FOSUserBundle'))
            ->add('username', null, array('label' => 'username', 'translation_domain' => 'FOSUserBundle'))
            ->add('plainPassword', RepeatedType::class, array(
                'type' => PasswordType::class,
                'options' => array(
                    'translation_domain' => 'FOSUserBundle',
                    'attr' => array(
                        'autocomplete' => 'new-password',
                    ),
                ),
                'first_options' => array('label' => 'password'),
                'second_options' => array('label' => 'password_confirmation'),
                'invalid_message' => 'fos_user.password.mismatch',
            ))/*->add('roles', ChoiceType::class, array('label' => 'Type ',
                'choices' => array(' PROFESSEUR' => 'ROLE_PROFESSEUR',
                    'ETUDIANT' => 'ROLE_ETUDIANT'),
                'required' => true, 'multiple' => true,))
            ->add('User')                 */->add('ajouter',SubmitType::class,['attr' => ['class' => 'btn btn-gradient-primary mr-2'],]);
    }


            /**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'UtilisateursBundle\Entity\User'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'utilisateursbundle_user';
    }


}
