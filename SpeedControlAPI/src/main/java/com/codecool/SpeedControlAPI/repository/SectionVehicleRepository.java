package com.codecool.SpeedControlAPI.repository;

import com.codecool.SpeedControlAPI.model.SectionVehicle;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Repository;

@Repository
public class SectionVehicleRepository implements JpaRepository<SectionVehicle, Long> {

    @Override
    public void flush() {

    }

    @Override
    public <S extends SectionVehicle> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends SectionVehicle> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<SectionVehicle> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public SectionVehicle getOne(Long aLong) {
        return null;
    }

    @Override
    public SectionVehicle getById(Long aLong) {
        return null;
    }

    @Override
    public SectionVehicle getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends SectionVehicle> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends SectionVehicle> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends SectionVehicle> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends SectionVehicle> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends SectionVehicle> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends SectionVehicle> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends SectionVehicle, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends SectionVehicle> S save(S entity) {
        return SectionVehicle.builder()
                .licensePlate(entity.getLicensePlate())
                .entryTime()
    }

    @Override
    public <S extends SectionVehicle> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<SectionVehicle> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<SectionVehicle> findAll() {
        return null;
    }

    @Override
    public List<SectionVehicle> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(SectionVehicle entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends SectionVehicle> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<SectionVehicle> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<SectionVehicle> findAll(Pageable pageable) {
        return null;
    }
}
