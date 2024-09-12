//package br.com.gjnv.petshop.service;
//
//import br.com.gjnv.petshop.model.Atendente;
//import br.com.gjnv.petshop.repository.AtendenteRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class AtendenteService {
//
//    @Autowired
//    private AtendenteRepository atendenteRepository;
//
//    public List<Atendente> findAll() {
//        return atendenteRepository.findAll();
//    }
//
//    public Optional<Atendente> findById(Long id) {
//        return atendenteRepository.findById(id);
//    }
//
//    public Atendente save(Atendente atendente) {
//        return atendenteRepository.save(atendente);
//    }
//
//    public Optional<Atendente> update(Long id, Atendente atendenteDetails) {
//        return atendenteRepository.findById(id).map(atendente -> {
//            atendente.setClientesAtendidos(atendenteDetails.getClientesAtendidos());
//            return atendenteRepository.save(atendente);
//        });
//    }
//
//    public boolean delete(Long id) {
//        return atendenteRepository.findById(id).map(atendente -> {
//            atendenteRepository.delete(atendente);
//            return true;
//        }).orElse(false);
//    }
//}