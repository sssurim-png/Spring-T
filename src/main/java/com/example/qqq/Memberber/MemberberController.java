package com.example.qqq.Memberber;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberberController {

///    📌  Response-get

    /// case1. 서버가 사용자에게 text데이터 return
    ///형식: http://localhost:8085/member/member
@GetMapping("/member")
@ResponseBody
    public String textDataReturn(){
        return "hong";
    }


    ///    case2. 서버가 사용자에게 json형식의 문자데이터 return
    @GetMapping("/json")
    @ResponseBody
    public Memberber jsonData(){ //type 맞춰주기
        Memberber m2 = new Memberber("gjkl",21);
        return m2;
    }


    ///    case3. 서버가 사용자에게 html return
    @GetMapping("/html")
    public String htmlReturn(){
        return "simple_html";
    }


    ///    case3. 서버가 사용자에게 html return
    @GetMapping("/html/dynamic")
    public String dynamicHtmlreturn(Model model){
        model.addAttribute("name", "hong");
        model.addAttribute("email", "hong@naver.com");
        return "dynamic_html";
    }


    ///  📌  Request-get: get요청의 url의 데이터 추출방식: pathvariable, 쿼리파라미터


    ///  📍  case1. pathvariable 방식을 통해 사용자로부터 url에서 데이터 추출  //서버로 요청 들어왔을 때 추출해야한다

    @GetMapping("/path/{inputId}")
    @ResponseBody
    public String path(@PathVariable Long inputId){
        System.out.println(inputId);
        return "ok";
    }

    ///  📍  case2. parameter방식을 통한 url에서의 데이터 추출(주로 검색, 정렬, 요청 등의 상황에서 사용
    ///     case2-1) 1개의파라미터에서 데이터 추출

    @GetMapping("/param1")
    @ResponseBody
    public String param1(@RequestParam(value = "name")String inputName){//빼서 담을 곳 필요
        System.out.println(inputName);
        return "ok";
    }


    /// 📍   case2-2) 2개의파라미터에서 데이터 추출
    ///    데이터 형식: member/param2?name=hongildong&email=hong@naver.com  //띄어쓰기 안됌!!!

    @GetMapping("/param2")
    @ResponseBody
    public String param2(@RequestParam(value = "name")String inputName,
                         @RequestParam(value = "email")String inputEmail){
        System.out.println(inputName);
        System.out.println(inputEmail);
        return "ok";
    }

    /// 📍   case2-3) 파라미터의 개수가 많아질 경우, ModelAttribute를 통한 데이터바인딩
    ///     데이터바인딩은  param의 데이터를 모아 객체로 자동 매핑 및 생성 -자료들을 그냥 객체로 만들어서 받아주겠다
    @GetMapping("param3")
    @ResponseBody
    public String param3(@ModelAttribute Memberber memberber) {//맞는 객체형태에 따른 값이 들어오면 객체로 만들어서 memberber에 저장
        System.out.println(memberber);
        return "ok";


    }
///  📌  post요청 처리 case: urlencoded, multipart-formdata, json
/// 📍  case1. body의 content-type이 utlencoded형식
///      형식: body부에 name=hongildong@email=hong@naver.com
        @PostMapping("/url-encoded") //형식이url의 파라미터 방식과 동일, RequestParam 또는 데이터바인딩 가능
        @ResponseBody
        public String urlEncoded(@ModelAttribute Memberber memberber2){
            System.out.println(memberber2);
            return "ok";
        }


    /// 📍   case2. body의 content-type이 multipart-formdata
    ///     case2-1) 1개의 이미지만 있는 경우
    //      형식: body부에 name=hongildong@email=hong@naver.com&profileImage=xxxx(바이너리 데이터)
    @PostMapping("/multipart-formdata")
    @ResponseBody
    public String multipartFordata(@ModelAttribute Memberber memberber3, @RequestParam(value = "profileImage")MultipartFile profileImage){
        System.out.println(memberber3);
        System.out.println(profileImage.getOriginalFilename());
        return "ok";
    }


    ///    case2. body의 content-type이 multipart-formdata
    ///📍     case2-2) 여러개의 이미지가 있는 경우

    @PostMapping("/multipart-formdata-image")
    @ResponseBody
    public String multipartFormdataImage(@ModelAttribute Memberber memberber, @RequestParam(value = "profileImage") List<MultipartFile> profileImage){
        System.out.println(memberber);
        System.out.println(profileImage.size());
        return "ok";
    }



    /// 📍   case3. body의 content-type이 json
    ///     case3-1)일반적인 json데이터 처리
    ///     형식: {"name":"hongildong","email":"hong@naver.com"} <-body json에 붙여넣기
    @PostMapping("/json")
    @ResponseBody
    public String json(@RequestBody Memberber memberber){
        System.out.println(memberber);
        return "ok";
    }




    /// 📍    case3-2)일반적인 json데이터 처리
    /// ///     형식: [{"name":"hongildong1","email":"hong1@naver.com"},{"name":"hongildong2","email":"hong2@naver.com"},{"name":"hongildong3","email":"hong3@naver.com"}] <-body json에 붙여넣기
    @PostMapping("json-list")
    @ResponseBody
    public String jsonList(@RequestBody List<Memberber>memberbersList){
        System.out.println(memberbersList);
        return "ok";
    }



     /// 📍   case 3-3)중첩된 json 데이터 처리
     ///    데이터형식:{"name":"hongildong", "email":"hong1@naver.com", "scores":[{"subject":"math", "point":100}, {"subject":"english", "point":90}, {"subject":"korean", "point":100}]}
    @PostMapping("/json-nested")
    @ResponseBody
    public String jsonnested(@RequestBody Student student){
        System.out.println(student);
        return "ok";
    }



     /// 📍   case3-4)json +file이 함께 있는 데이터 처리
     ///    데이터형식: member={"name':"xx","email":"yy"}&profileImage=바이너리
     ///    결론은 multipart-formdata구조안에 json을 넣는 방식
    @PostMapping("/json-file")
    @ResponseBody
    public String jsonFile(@RequestPart("member")Memberber memberber, //json
                           @RequestPart("profileImage")MultipartFile profileImage){
        System.out.println(memberber);
        System.out.println(profileImage);
        return "ok";


    }

}
