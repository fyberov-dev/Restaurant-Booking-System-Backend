package ee.nimens.restaraunt.booking.system.backend.controller;

import ee.nimens.restaraunt.booking.system.backend.dto.request.table.CreateTableRequestDto;
import ee.nimens.restaraunt.booking.system.backend.dto.request.table.UpdateTableRequestDto;
import ee.nimens.restaraunt.booking.system.backend.dto.table.TableDto;
import ee.nimens.restaraunt.booking.system.backend.service.TableService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
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
    public ResponseEntity<TableDto> createTable(@RequestBody CreateTableRequestDto dto) {
        TableDto table = tableService.create(dto);

        log.debug("New table created. id={}, guests={}, x={}, y={}",
            table.id(),
            table.guests(),
            table.x(),
            table.y()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(table);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TableDto> updateTable(@PathVariable long id, @RequestBody UpdateTableRequestDto dto) {
        Optional<TableDto> tableOptional = tableService.update(id, dto);

        if (tableOptional.isPresent()) {
            TableDto tableDto = tableOptional.get();

            log.debug("Table updated. id={}, guests={}, x={}, y={}",
                tableDto.id(),
                tableDto.guests(),
                tableDto.x(),
                tableDto.y()
            );

            return ResponseEntity.status(HttpStatus.OK).body(tableDto);
        } else {
            log.debug("Table NOT updated. id={}", id);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTable(@PathVariable long id) {
        tableService.delete(id);

        log.debug("Table deleted. id={}", id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
