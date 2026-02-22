package ee.nimens.restaraunt.booking.system.backend.service;

import ee.nimens.restaraunt.booking.system.backend.dto.request.table.CreateTableRequestDto;
import ee.nimens.restaraunt.booking.system.backend.dto.request.table.UpdateTableRequestDto;
import ee.nimens.restaraunt.booking.system.backend.dto.table.TableDto;
import ee.nimens.restaraunt.booking.system.backend.entity.TableEntity;
import ee.nimens.restaraunt.booking.system.backend.exception.table.TableNotFoundException;
import ee.nimens.restaraunt.booking.system.backend.repository.TableRepository;
import ee.nimens.restaraunt.booking.system.backend.util.mapper.TableMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TableService {

    private final TableRepository tableRepository;
    private final TableMapper tableMapper;

    public List<TableDto> getAll() {
        return tableMapper.toDtos(tableRepository.findAll());
    }

    public TableDto getById(long id) {
        return tableMapper.toDto(getEntityById(id));
    }
    
    public TableDto create(CreateTableRequestDto dto) {
        TableEntity table = TableEntity.builder()
            .guests(dto.guests())
            .posX(dto.x())
            .posY(dto.y())
            .build();

        return tableMapper.toDto(tableRepository.save(table));
    }

    public Optional<TableDto> update(long id, UpdateTableRequestDto dto) {
        TableEntity table = getEntityById(id);

        boolean isUpdated = false;

        if (isUpdated(table.getGuests(), dto.guests())) {
            table.setGuests(dto.guests());
            isUpdated = true;
        }

        if (isUpdated(table.getPosX(), dto.x())) {
            table.setPosX(dto.x());
            isUpdated = true;
        }

        if (isUpdated(table.getPosY(), dto.y())) {
            table.setPosY(dto.y());
            isUpdated = true;
        }

        if (isUpdated) {
            TableEntity tableEntity = tableRepository.save(table);
            return Optional.of(tableMapper.toDto(tableEntity));
        }

        return Optional.empty();
    }

    public void delete(long id) {
        TableEntity table = getEntityById(id);

        tableRepository.delete(table);
    }

    private <T> boolean isUpdated(T current, T updated) {
        return updated != null && !updated.equals(current);
    }

    private TableEntity getEntityById(long id) {
        return tableRepository.findById(id)
            .orElseThrow(TableNotFoundException::new);
    }
}
