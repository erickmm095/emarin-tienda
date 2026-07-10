package com.tiendaTech.tienda.controller;

import com.tiendaTech.tienda.service.CategoriaService;
import com.tiendaTech.tienda.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    public ConsultaController(ProductoService productoService,
            CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        var productos = productoService.getProductos(false);
        var categorias = categoriaService.getCategorias(true);

        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categorias);

        return "/consultas/listado";
    }

    @PostMapping("/consultaDerivada")
    public String consultaDerivada(@RequestParam double precioInf,
            @RequestParam double precioSup,
            Model model) {

        var lista = productoService.consultaDerivada(precioInf, precioSup);

        model.addAttribute("productos", lista);
        model.addAttribute("categorias", categoriaService.getCategorias(true));
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);

        return "/consultas/listado";
    }

    @PostMapping("/consultaJPQL")
    public String consultaJPQL(@RequestParam double precioInf,
            @RequestParam double precioSup,
            Model model) {

        var lista = productoService.consultaJPQL(precioInf, precioSup);

        model.addAttribute("productos", lista);
        model.addAttribute("categorias", categoriaService.getCategorias(true));
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);

        return "/consultas/listado";
    }

    @PostMapping("/consultaSQL")
    public String consultaSQL(@RequestParam double precioInf,
            @RequestParam double precioSup,
            Model model) {

        var lista = productoService.consultaSQL(precioInf, precioSup);

        model.addAttribute("productos", lista);
        model.addAttribute("categorias", categoriaService.getCategorias(true));
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);

        return "/consultas/listado";
    }

    @PostMapping("/consultaAmpliada")
    public String consultaAmpliada(@RequestParam Integer idCategoria,
            @RequestParam Integer existencias,
            Model model) {

        var lista = productoService.consultaAmpliada(idCategoria, existencias);

        model.addAttribute("productos", lista);
        model.addAttribute("categorias", categoriaService.getCategorias(true));
        model.addAttribute("idCategoria", idCategoria);
        model.addAttribute("existencias", existencias);

        return "/consultas/listado";
    }
}