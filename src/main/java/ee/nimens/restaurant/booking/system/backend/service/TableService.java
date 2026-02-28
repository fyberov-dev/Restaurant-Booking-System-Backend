package ee.nimens.restaurant.booking.system.backend.service;

import ee.nimens.restaurant.booking.system.backend.dto.request.table.CreateTableRequestDto;
import ee.nimens.restaurant.booking.system.backend.dto.request.table.UpdateTableRequestDto;
import ee.nimens.restaurant.booking.system.backend.dto.table.TableDto;
import ee.nimens.restaurant.booking.system.backend.entity.TableEntity;
import ee.nimens.restaurant.booking.system.backend.exception.table.TableNotFoundException;
import ee.nimens.restaurant.booking.system.backend.repository.TableRepository;
import ee.nimens.restaurant.booking.system.backend.util.mapper.TableMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TableService {

    private final TableRepository tableRepository;
    private final TableMapper tableMapper;

    /**
     * Get full list of tables.
     *
     * @return list of tables dtos
     */
    public List<TableDto> getAll() {
        return tableMapper.toDtos(findAll());
    }

    /**
     * Get table using id.
     *
     * @param id id of the table to find
     * @return dto of a found table
     */
    public TableDto getById(long id) {
        return tableMapper.toDto(findById(id));
    }

    /**
     * Create new table in a restaurant.
     *
     * @param dto data to create a table that contains:
     *            - number of guests for a table
     *            - position of a table in the restaurant
     * @return dto of a created table
     */
    public TableDto create(CreateTableRequestDto dto) {
        TableEntity table = TableEntity.builder()
            .guests(dto.guests())
            .posX(dto.x())
            .posY(dto.y())
            .isVertical(dto.isVertical() == null ? false : dto.isVertical())
            .build();

        TableEntity createdTable = tableRepository.save(table);

        log.debug("New table created. id={}, guests={}, x={}, y={}, isVertical={}",
            createdTable.getId(),
            createdTable.getGuests(),
            createdTable.getPosX(),
            createdTable.getPosY(),
            createdTable.isVertical()
        );

        return tableMapper.toDto(createdTable);
    }

    /**
     * Update table.
     *
     * @param id id of the table to update
     * @param dto data to update table
     * @return dto of an updated table if it was updated, otherwise Optional.Empty()
     */
    public Optional<TableDto> update(long id, UpdateTableRequestDto dto) {
        TableEntity table = findById(id);

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

        if (isUpdated(table.isVertical(), dto.isVertical())) {
            table.setVertical(dto.isVertical());
            isUpdated = true;
        }

        if (isUpdated) {
            TableEntity updatedTable = tableRepository.save(table);

            log.debug("Table updated. id={}, guests={}, x={}, y={}, isVertical={}",
                updatedTable.getId(),
                dto.guests() != null ? dto.guests() : "",
                dto.x(),
                dto.y(),
                dto.isVertical()
            );

            return Optional.of(tableMapper.toDto(updatedTable));
        }

        log.debug("No changes made for the table. id={}", id);

        return Optional.empty();
    }

    /**
     * Delete table.
     *
     * @param id id of the table to delete
     */
    public void delete(long id) {
        TableEntity table = findById(id);

        log.debug("Table deleted. id={}", id);

        tableRepository.delete(table);
    }

    /**
     * Get table entity using id.
     *
     * @param id id of the table to find
     * @return found table entity
     * @throws TableNotFoundException if table doesn't exist
     */
    public TableEntity findById(long id) {
        return tableRepository.findById(id)
            .orElseThrow(TableNotFoundException::new);
    }

    /**
     * Find all the tables in a restaurant.
     *
     * @return all the tables at the restaurant
     */
    public List<TableEntity> findAll() {
        return tableRepository.findAll();
    }

    /**
     * Check if object is provided and it differs from current object.
     *
     * @param current value of the existing object
     * @param updated value to update current object with
     * @param <T> objects that are compared
     * @return true if object is updated, otherwise false
     */
    private <T> boolean isUpdated(T current, T updated) {
        return updated != null && !updated.equals(current);
    }

}
