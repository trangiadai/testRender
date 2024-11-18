package org.example.miniforum.dataInitial;

import org.example.miniforum.dto.request.AuthenticationRequest;
import org.example.miniforum.model.*;
import org.example.miniforum.repository.CategoryRepository;
import org.example.miniforum.repository.CommentRepository;
import org.example.miniforum.repository.PostRepository;
import org.example.miniforum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {




        User user = User.builder()
                .username("luc tran")
                .email("tluc509@gmail.com")
                .password("123456")
                .accountName("anh luc rach gia")
                .build();
        userRepository.save(user);

        Image image = new Image();
        image.setImageUrl("https://encrypted-tbn1.gstatic.com/shopping?q=tbn:ANd9GcTxJIoVWWupMZmd8zvRJnUVCPWHho2Y4QHIDErylcMjb-X_WhTNgbp-Sr9fDJd-Mqu-9eAU0VqepffxPC08RaM3w8NU8gzKE5cnFAfHOgT9INKCkR1sSZjeaaxVlQGDeShly1Ie6A&usqp=CAc");


        List<Image> images = new ArrayList<>();
        images.add(image);


        Category ct1 = new Category();
        ct1.setName("Thời trang");
        Category ct2 = new Category();
        ct2.setName("sách");
        Category ct3 = new Category();
        ct3.setName("Đồ công nghệ");
        Category ct4 = new Category();
        ct4.setName("Dụng cụ, thiết bị");
        Category ct5 = new Category();
        ct5.setName("Thời trang");

        List<Category> cts = new ArrayList<>();
        cts.add(ct1);
        cts.add(ct2);
        cts.add(ct3);
        cts.add(ct4);
        cts.add(ct5);
        categoryRepository.saveAll(cts);

        Set<Category> categories = new HashSet<>();
        categories.add(categoryRepository.findById(1).get());

        Post post = Post.builder()
                .images(images)
                .title("Bán đồ hốt rác")
                .content("hàng chính hãng China chất liệu nhựa cao cấp")
                .price(45000L)
                .votes(0L)
                .comments(0L)
                .user(user)
                .categories(categories)
                .build();

        Set<Category> cgr = new HashSet<>();
        cgr.add(categoryRepository.findById(3).get());
        Post post2 = Post.builder()
                .title("tìm gia sư")
                .content("cần tìm gia sư hoặc người trông trẻ cho bé 240 tháng tuổi vào ban đêm, yêu cầu nữ 16 đến 25t, cao trên 1m55 nặng dưới 60kg, ưu tiên ngoại hình" +
                        "lương thử việc 100k/h")
                .price(450000L)
                .votes(0L)
                .comments(0L)
                .user(user)
                .categories(cgr)
                .build();


        postRepository.save(post2);

        for(Image img : images) {
            img.setPost(post);
        }

        Set<Post> ps = new HashSet<Post>();
        ps.add(post);
        ct1.setPosts(ps);





        postRepository.save(post);

        Comment comment = Comment.builder()
                .content("cho xin sdt")
                .post(post)
                .user(user)
                .build();
        commentRepository.save(comment);
//
//
//        Post post1 = postRepository.findById(post.getId()).get();
//        post1.setComment(comment);
//        postRepository.save(post1);
        AuthenticationRequest request = AuthenticationRequest.builder()
                .username("luc tran")
                .password("123456")
                .build();
    }
}