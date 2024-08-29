package com.day4.spring.security.task1.bukuLog;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/buku")
@Validated
@Log4j2
public class BukuController {

    @Autowired
    private BukuRepository bukuRepository;


    private final Counter successCounter;
    private final Counter failureCounter;

    @Autowired
    public BukuController(MeterRegistry meterRegistry) {

        this.successCounter = meterRegistry.counter("api.request.success.count");
        this.failureCounter = meterRegistry.counter("api.request.failure.count");
    }


    @GetMapping
    public List<BukuModel> getAllBuku() {
        return bukuRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<BukuModel> getBukuById(@PathVariable("id") @Positive Long id) {
        Optional<BukuModel> buku = bukuRepository.findById(id);

        if(buku.isPresent())
        {
            successCounter.increment();
        }
        else
        {
            failureCounter.increment();
        }

        return buku.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<BukuModel> createBuku(@RequestBody @Valid BukuModel buku) {
        BukuModel savedBuku = bukuRepository.save(buku);
        if(!savedBuku.equals(null))
        {
            successCounter.increment();
        }
        else
        {
            failureCounter.increment();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBuku);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BukuModel> updateBukuAPI(@PathVariable("id") @Positive Long id, @RequestBody @Valid BukuModel buku) {
        BukuModel updatedBuku = updateBuku(id, buku);
        if(!updatedBuku.equals(null))
        {
            successCounter.increment();
        }
        else
        {
            failureCounter.increment();
        }
        return updatedBuku != null ? ResponseEntity.ok(updatedBuku) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public BukuModel updateBuku(Long id, BukuModel buku) {
        if (bukuRepository.existsById(id)) {
            buku.setId(id);
            successCounter.increment();
            return bukuRepository.save(buku);
        } else {
            failureCounter.increment();
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuku(@PathVariable("id") @Positive Long id) {
        Optional<BukuModel> buku = bukuRepository.findById(id);
        if (buku.isPresent()) {
            bukuRepository.deleteById(id);
            successCounter.increment();
            return ResponseEntity.noContent().build();
        } else {
            failureCounter.increment();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
