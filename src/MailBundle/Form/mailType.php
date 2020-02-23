<?php

namespace MailBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class mailType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('subject',TextType::class,['attr' => ['class' => 'form-control'],])
                ->add('mail',TextType::class,['attr' => ['class' => 'form-control'],])
                ->add('object',TextType::class,['attr' => ['class' => 'form-control'],])
                 ->add('envoyer',SubmitType::class,['attr' => ['class' => 'btn btn-gradient-primary mr-2'],]);
    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'MailBundle\Entity\mail'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'mailbundle_mail';
    }


}
