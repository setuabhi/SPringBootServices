Content:

1. spring-cloud-starter-netflix-eureka-client and spring-cloud-starter-gateway needed
2. see routes of application.yml
3. 🚀 u1 sends 21 requests instantly (replenishRate=10 and burst capacity is 20):
   Request #	Token Available	Allowed?	Explanation
   1 - 20	          20 → 1	✅ Yes	    Each request consumes 1 token.
   21	0	           ❌ No	No           token left = rate-limited.

    ⏱ What happens next?
    1 second later, 10 tokens are added (replenishRate = 10).
    
    So u1 can send 10 more requests after 1 second.
    
    This continues every second.