package org.launchcode.models.data;

import org.launchcode.models.data.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface CategoryDao extends CrudRepository<Category, Integer>{

}