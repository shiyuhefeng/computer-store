package com.huige.store.controller;

import com.huige.store.entity.Cart;
import com.huige.store.service.ICartService;
import com.huige.store.util.JsonResult;
import com.huige.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author huige
 * Create on 2022/4/15 13:33
 */
@RestController
@RequestMapping("/carts")
public class CartController extends BaseController {

    @Autowired
    private ICartService cartService;

    @RequestMapping("/add_to_cart")
    public JsonResult<Cart> addToCart(Integer pid, Integer amount, HttpSession session) {
        String username = getUsernameFromSession(session);
        Integer uid = getuidFromSession(session);
        cartService.insertCart(uid, pid, amount, username);
        return new JsonResult<>(OK);
    }

    @RequestMapping({"/", ""})
    public JsonResult<List<CartVO>> getVoListByUid(HttpSession session) {
        return new JsonResult<>(OK, "查询成功", cartService.getVoByUid(getuidFromSession(session)));
    }

    @RequestMapping("/{cid}/{num}/add")
    public JsonResult<Integer> addNum(HttpSession session, @PathVariable("cid") Integer cid, @PathVariable("num") String num) {
        Integer res = cartService.addNum(cid,
                getuidFromSession(session),
                getUsernameFromSession(session), Integer.parseInt(num));
        return new JsonResult<>(OK, "添加成功", res);
    }

    @RequestMapping("/list")
    public JsonResult<List<CartVO>> getVOByCid(Integer[] cids, HttpSession session) {
        List<CartVO> data = cartService.getVOCid(getuidFromSession(session), cids);
        return new JsonResult<>(OK, "查询成功", data);
    }
}
