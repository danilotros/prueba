# language: es
Característica:  Como usuario , Yo quiero ingresar a la plataforma Utest para ver todos los cursos

  @Escenario_1
  Escenario: Ingresar a la plataforma de Utest como usuario valido
    Dado Ya esta en la plataforma
    Cuando El ingrese las credenciales
      |correo|contra|
      |danielgarcia10211@gmail.com|TGGadll94$|
    Entonces El vera la pantalla principal
      |texto|
      | MY ACTIVITY |


