//package com.cb.server_system.service;
//
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.cb.pojo.entity.Dish;
//import com.cb.server_system.mapper.DishMapper;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//public class DishService extends ServiceImpl<DishMapper,Dish> {//继承mplus的ServiceImpl
//    @Autowired
//    private DishMapper dishMapper;
//    /**
//     * 查找菜品
//    **/
//    public Dish selectById (Long id){
//        return dishMapper.selectById(id);
//    }
//    public boolean  page(Long id){
//        return dishMapper.deleteById(id)>0;
//    }
//    public boolean  deleteById(Long id){
//        return dishMapper.deleteById(id)>0;
//    }
//    @Transactional
//    public void saveWithFlavor(DishDTO dishDTO) {
//
//        Dish dish = new Dish();
//
//        BeanUtils.copyProperties(dishDTO, dish);
//
//        //向菜品表插入1条数据
//        dishMapper.insert(dish);
//
//        //获取insert语句生成的主键值
//        Long dishId = dish.getId();
//
//        List<DishFlavor> flavors = dishDTO.getFlavors();
//        if (flavors != null && flavors.size() > 0) {
//            flavors.forEach(dishFlavor -> {
//                dishFlavor.setDishId(dishId);
//            });
//            //向口味表插入n条数据
//            dishFlavorMapper.insertBatch(flavors);
//        }
//    }
//
//
//
//
//}
