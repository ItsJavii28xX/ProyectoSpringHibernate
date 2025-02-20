package com.javijaime.demo;

import com.javijaime.demo.service.ServicioAutenticacion;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ServicioAutenticacionTest {

    private final ServicioAutenticacion servicioAutenticacion = new ServicioAutenticacion();

    @Test
    public void generarToken_deberiaGenerarTokenValido() {
        String token = servicioAutenticacion.generarToken("testuser");
        assertNotNull(token);
        assertTrue(servicioAutenticacion.validarToken(token));
    }

    @Test
    public void validarToken_deberiaValidarTokenValido() {
        String token = servicioAutenticacion.generarToken("testuser");
        assertTrue(servicioAutenticacion.validarToken(token));
    }

    @Test
    public void validarToken_deberiaRechazarTokenInvalido() {
        assertFalse(servicioAutenticacion.validarToken("token.invalido"));
    }
}