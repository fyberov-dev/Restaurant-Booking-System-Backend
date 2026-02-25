package ee.nimens.restaurant.booking.system.backend.controller;

import ee.nimens.restaurant.booking.system.backend.dto.request.table.CreateTableRequestDto;
import ee.nimens.restaurant.booking.system.backend.dto.request.table.UpdateTableRequestDto;
import ee.nimens.restaurant.booking.system.backend.dto.table.TableDto;
import ee.nimens.restaurant.booking.system.backend.service.TableService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/table")
@RequiredArgsConstructor
public class TableController {

    private final TableService tableService;

    @GetMapping
    public ResponseEntity<List<TableDto>> getTables() {
        return ResponseEntity.status(HttpStatus.OK).body(tableService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TableDto> getTableById(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(tableService.getById(id));
    }

    @PostMapping
    public ResponseEntity<TableDto> createTable(@RequestBody @Valid CreateTableRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tableService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TableDto> updateTable(@PathVariable long id, @RequestBody @Valid UpdateTableRequestDto dto) {
        Optional<TableDto> tableOptional = tableService.update(id, dto);

        if (tableOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(tableOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTable(@PathVariable long id) {
        tableService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
