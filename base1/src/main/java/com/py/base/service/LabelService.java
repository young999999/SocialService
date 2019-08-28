package com.py.base.service;

import com.py.base.dao.LabelDao;
import com.py.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class LabelService {
    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;

    public List<Label> findAll(){
        return  labelDao.findAll();
    }

    public Label findById(String id){
        return labelDao.findById(id).get();
    }

    public void save(Label label){
        label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }

    public void updata(Label label){
        labelDao.save(label);
    }

    public void deleteById(String id){
        labelDao.deleteById(id);
    }


    //条件查询
    public List<Label> findSearch(Label label) {
        return labelDao.findAll(new Specification<Label>() {
            /**
             *
             * @param root 根对象，也就是要把条件封装到那个对象中。where 类名=table.getid
             * @param query 封装的都是查询关键字，比如group by、order by
             * @param cb 用来封装条件对象，如果直接返回null，表示不需要任何条件
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //list集合，用来存放所有条件
                List<Predicate> list=new ArrayList<>();
                if (label.getLabelname()!=null&&!"".equals(label.getLabelname())){
                    Predicate predicate=cb.like(root.get("labelname").as(String.class),"%"+label.getLabelname()+"%");//where labelname like "%小明%"
                    list.add(predicate);
                }
                if (label.getState()!=null&&!"".equals(label.getState())){
                    Predicate predicate=cb.equal(root.get("state").as(String.class),label.getState());//where state="1"
                    list.add(predicate);
                }


                //new一个数组作为最终返回值的条件
                Predicate[] predicates=new  Predicate[list.size()];
                //把list直接转换成数组
                predicates=list.toArray(predicates);
                return cb.and(predicates);//where labelname like "%小明%" and state="1"
            }
        });
    }


    public Page<Label> pageQuery(Label label, int page, int size) {
        //封装分页对象
        Pageable pageable= PageRequest.of(page-1,size);
        return labelDao.findAll(new Specification<Label>() {
            /**
             *
             * @param root 根对象，也就是要把条件封装到那个对象中。where 类名=table.getid
             * @param query 封装的都是查询关键字，比如group by、order by
             * @param cb 用来封装条件对象，如果直接返回null，表示不需要任何条件
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //list集合，用来存放所有条件
                List<Predicate> list=new ArrayList<>();
                if (label.getLabelname()!=null&&!"".equals(label.getLabelname())){
                    Predicate predicate=cb.like(root.get("labelname").as(String.class),"%"+label.getLabelname()+"%");//where labelname like "%小明%"
                    list.add(predicate);
                }
                if (label.getState()!=null&&!"".equals(label.getState())){
                    Predicate predicate=cb.equal(root.get("state").as(String.class),label.getState());//where state="1"
                    list.add(predicate);
                }


                //new一个数组作为最终返回值的条件
                Predicate[] predicates=new  Predicate[list.size()];
                //把list直接转换成数组
                predicates=list.toArray(predicates);
                return cb.and(predicates);//where labelname like "%小明%" and state="1"
            }
        },pageable);
    }
}
