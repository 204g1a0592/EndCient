package com.edureka.training.controller;

import java.security.Principal;
import java.util.HashMap;


import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edureka.training.entity.CartItem;
import com.edureka.training.entity.Login;
import com.edureka.training.entity.Product;
import com.edureka.training.entity.UserCredentail;

import jakarta.servlet.http.HttpSession;


import org.springframework.http.HttpHeaders;

@Controller
@RequestMapping("/client")
public class UserController {
	
	@GetMapping("ViewCart")
	public String cart() {
		return "ViewCart";
	}
	@GetMapping("/products")
	public String getAllProducts(Model model, @ModelAttribute Login user) {
	    RestTemplate restTemplate = new RestTemplate();
	    String apiUrl = "http://localhost:8091/api/allProducts"; // Match your REST endpoint

	    ResponseEntity<Product[]> response = restTemplate.getForEntity(apiUrl, Product[].class);
	    Product[] products = response.getBody();

	    model.addAttribute("username", user.getName());
	    model.addAttribute("products", products);

	    return "user_dashboard"; // This should be the page you styled
	}

	@GetMapping("/userlogin")
	public String userlogin() {
		return "user";
	}
	@GetMapping("adminlogin")
	public String adminlogin() {
		return "admin";
	}
	@GetMapping("register")
	public String register() {
		return "register";
	}
	@GetMapping("product")
	public String product() {
		return "product_management";
	}
	@GetMapping("vendor")
	public String vendor() {
		return "vendor_management";
	}
	@GetMapping("add_vendor")
	public String addvendor() {
		return "addVendor";
	}
	@GetMapping("fordashboard")

	public String dashboard() {
		return "admin_dashboard";
	}

	@GetMapping("addproduct")

	public String addproduct() {
		return "addProduct";
	}


	
//	
//	@PostMapping("loginsuccess")
//	
//	public String dashboard(@ModelAttribute Login user, Model model) {
//	    String apiUrl = "http://localhost:8091/api/loginvalidation"; 
//	    RestTemplate restTemplate = new RestTemplate();
//	    ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, user, String.class);
//	    System.out.println("respones.........."+response);
//	    if(response.getBody().contains(user.getName())) {
//	    	 model.addAttribute("username", user.getName()); // e.g., "John Doe"
//	 	    return "user_dashboard";
//	    //	 return "user_dashboard";
//	    	
//	    }
//	    else {
//	    	model.addAttribute("error", "Invalid Credentails");
//	    	return "user";
//	    }
//	}
	
	@PostMapping("loginsuccess")
	public String dashboard(@ModelAttribute Login user, Model model, HttpSession session) {
	    String apiUrl = "http://localhost:8091/api/loginvalidation";
	    RestTemplate restTemplate = new RestTemplate();

	    ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, user, String.class);

	    if (response.getBody() != null && response.getBody().contains(user.getName())) {
	        // Login success, now fetch user info to get userId
	        String userInfoUrl = "http://localhost:8091/api/user/" + user.getName();
	        ResponseEntity<UserCredentail> userInfoResponse = restTemplate.getForEntity(userInfoUrl, UserCredentail.class);

	        if (userInfoResponse.getStatusCode() == HttpStatus.OK && userInfoResponse.getBody() != null) {
	            UserCredentail loggedInUser = userInfoResponse.getBody();
	            session.setAttribute("userId", loggedInUser.id());    // save userId in session
	            session.setAttribute("username", loggedInUser.name()); // save username in session

	            model.addAttribute("username", loggedInUser.name());
	            return "user_dashboard";
	        } else {
	            model.addAttribute("error", "User info not found");
	            return "user";
	        }
	    } else {
	        model.addAttribute("error", "Invalid Credentials");
	        return "user";
	    }
	}

	
	@PostMapping("admin")
	public String admin(@ModelAttribute Login user, Model model) {
		String apiUrl="http://localhost:8091/api/admin";
		RestTemplate resttemplate=new RestTemplate();
		ResponseEntity<String> response= resttemplate.postForEntity(apiUrl, user, String.class);
		System.out.println("responde form aadmin..."+response);
		if(response.getBody().contains("Samyuktha") && response.getBody().contains("123456")) {
			model.addAttribute("mess", "Samyuktha");
			return "admin_dashboard";
		}
		else {
			model.addAttribute("error", "Invalid credentails");
			return "admin";
		}
		
		
	}
	 @PostMapping("/register")
	    public String registerUser(@ModelAttribute UserCredentail user, Model model) {
		
	        String apiUrl = "http://localhost:8091/api/register";  // REST API endpoint
	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, user, String.class);
	        System.out.println("response......"+response);
	        if (!response.getBody().contains("null")) {
	        	System.out.println("succufull......."+response.getBody().contains("null"));
	            return "redirect:/client/userlogin";  // Redirect to login page
	        } else if( response.getBody().toString().substring(18, 22).equalsIgnoreCase("null")){
	            model.addAttribute("error", "User Name already exists");
	            return "register";  // Show registration form again
	        }
	        else {//if(response.getBody().toString().substring(response.getBody().indexOf('e'), 36).equalsIgnoreCase("null")){
	        	model.addAttribute("error", "User with email already exists");
	            return "register";
	        }
	 }
	 
	 // for  cart
//	 @PostMapping("/addToCart")
//	    public String addToCart(@RequestParam Long productId,
//	                            @RequestParam Integer quantity,
//	                            HttpSession session,
//	                            RedirectAttributes redirectAttributes) {
//
//	        Long userId = (Long) session.getAttribute("userId");
//	        if (userId == null) {
//	            redirectAttributes.addFlashAttribute("error", "User not logged in.");
//	            return "redirect:/client/userlogin";
//	        }
//
//	        RestTemplate restTemplate = new RestTemplate();
//
//	        Map<String, Object> cartRequest = new HashMap<>();
//	        cartRequest.put("userid", userId);
//	        cartRequest.put("productid", productId);
//	        cartRequest.put("quantity", quantity);
//
//	        HttpHeaders headers = new HttpHeaders();
//	        headers.setContentType(MediaType.APPLICATION_JSON);
//
//	        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(cartRequest, headers);
//
//	        try {
//	            restTemplate.postForEntity("http://localhost:8091/api/add", requestEntity, Void.class);
//	            redirectAttributes.addFlashAttribute("message", "Added to cart successfully!");
//	        } catch (Exception e) {
//	            redirectAttributes.addFlashAttribute("error", "Failed to add to cart: " + e.getMessage());
//	        }
//
//	        return "redirect:/client/products"; // redirect to product list page
//	    }
	 @PostMapping("/add/{productId}")
     public String addToCart(@PathVariable Long productId,
                             @RequestParam int quantity,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) {
RestTemplate restTemplate=new RestTemplate();
         Long userId = (Long) session.getAttribute("userId"); // assuming you store userId in session after login
         if (userId == null) {
             return "redirect:/login"; // user not logged in
         }

         try {
             // Call backend service method directly or via RestTemplate without token
             restTemplate.postForEntity(
                 "http://localhost:8091/api" + "/cart/add/" + productId + "?quantity=" + quantity + "&userId=" + userId,
                 null,
                 String.class);

             redirectAttributes.addFlashAttribute("success", "Product added to cart");
         } catch (Exception ex) {
             redirectAttributes.addFlashAttribute("error", "Error: " + ex.getMessage());
         }

         return "userproducts";
     }


//	 @PostMapping("/addToCart/{id}")
//	 public String addToCart(@PathVariable Long id, Principal principal) {
//	     String username = principal.getName();
//	     String apiUrl = "http://localhost:8080/api/cart/add";
//
//	     // Create request body to send to API
//	     Map<String, Object> request = new HashMap<>();
//	     request.put("username", username);
//	     request.put("productId", id);
//	     request.put("quantity", 1); // Default quantity 1
//	     RestTemplate restTemplate = new RestTemplate();
//	     restTemplate.postForObject(apiUrl, request, String.class);
//
//	     // Redirect to cart after adding
//	     return "";
//	 }
	 @GetMapping("/cart")
	 public String viewCart(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
	     Long userId = (Long) session.getAttribute("userId");
	     if (userId == null) {
	         redirectAttributes.addFlashAttribute("error", "Please login first.");
	         return "redirect:/client/userlogin";
	     }

	     RestTemplate restTemplate = new RestTemplate();
	     String apiUrl = "http://localhost:8091/api/cart/" + userId;

	     ResponseEntity<CartItem[]> response = restTemplate.getForEntity(apiUrl, CartItem[].class);
	     CartItem[] cartItems = response.getBody();

	     model.addAttribute("cartItems", cartItems);
	     model.addAttribute("username", session.getAttribute("username"));
	   //  System.out.println("fomr view cart"+);
	     return "ViewCart";  // your Thymeleaf template name for cart
	 }


	}
	




