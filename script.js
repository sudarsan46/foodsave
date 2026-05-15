const API = "http://localhost:8080";
let currentUser = null;
let selectedFood = null;

// LOGIN
async function login(){
    const email = document.getElementById("login-email").value;
    const pass = document.getElementById("login-pass").value;

    const res = await fetch(`${API}/user/login?emailid=${email}&password=${pass}`, {method:"POST"});
    const data = await res.text();

    const parts = data.split(":");

    currentUser = {
        role: parts[1],
        id: parseInt(parts[2]),
        email: email
    };

    initUI();
}

// REGISTER
async function register(){
    const name = document.getElementById("reg-name").value;
    const email = document.getElementById("reg-email").value;
    const pass = document.getElementById("reg-pass").value;
    const role = document.getElementById("reg-role").value;

    const res = await fetch(`${API}/user/register?name=${name}&emailid=${email}&password=${pass}&role=${role}`, {method:"POST"});
    alert(await res.text());
}

// UI INIT
function initUI(){
    document.getElementById("auth-section").style.display="none";
    document.getElementById("dashboard").style.display="block";

    document.getElementById("nav-user").innerHTML =
        `${currentUser.email} (${currentUser.role})
        <button onclick="logout()">Logout</button>`;

    if(currentUser.role==="USER"){
        document.getElementById("user-section").style.display="block";
        loadFood();
        loadUserOrders();
    } else {
        document.getElementById("provider-section").style.display="block";
        loadProviderOrders();
    }
}

// LOAD FOOD
async function loadFood(){
    const res = await fetch(`${API}/food/available?userId=${currentUser.id}`);
    const foods = await res.json();

    const container = document.getElementById("food-list");
    container.innerHTML="";

    foods.forEach(f=>{
        container.innerHTML += `
        <div class="card food">
            <h4>${f.foodname}</h4>
            <p>₹${f.price}</p>
            <p>Qty: ${f.quantity}</p>
            <button onclick="openModal(${f.foodid})">Order</button>
        </div>`;
    });
}

// USER ORDERS
async function loadUserOrders(){
    const res = await fetch(`${API}/order/user?userId=${currentUser.id}`);
    const data = await res.json();

    const container = document.getElementById("user-orders");
    container.innerHTML="";

    data.forEach(o=>{
        container.innerHTML += `
        <div class="card">
            <h4>${o.foodName}</h4>
            <p>Qty: ${o.quantity}</p>
        </div>`;
    });
}

// PROVIDER ORDERS
async function loadProviderOrders(){
    const res = await fetch(`${API}/order/provider?providerId=${currentUser.id}`);
    const data = await res.json();

    const container = document.getElementById("provider-orders");
    container.innerHTML="";

    data.forEach(o=>{
        container.innerHTML += `
        <div class="card">
            <h4>${o.foodName}</h4>
            <p>Qty: ${o.quantity}</p>
            <p>User: ${o.userName}</p>
        </div>`;
    });
}

// ADD FOOD
async function addFood(){
    const f = document.getElementById("food-name").value;
    const q = document.getElementById("food-qty").value;
    const p = document.getElementById("food-price").value;
    const l = document.getElementById("food-loc").value;

    const expire = Math.floor(Date.now()/1000)+86400;

    await fetch(`${API}/food/available?foodname=${f}&quantity=${q}&price=${p}&expireTime=${expire}&location=${l}&providerId=${currentUser.id}`, {method:"POST"});

    alert("Added");
}

// MODAL
function openModal(id){
    selectedFood = id;
    document.getElementById("modal").style.display="flex";
}

function closeModal(){
    document.getElementById("modal").style.display="none";
}

// PLACE ORDER
async function placeOrder(){
    const qty = document.getElementById("qty").value;

    await fetch(`${API}/order/place?userId=${currentUser.id}&foodId=${selectedFood}&quantity=${qty}`, {method:"POST"});

    closeModal();
    loadFood();
    loadUserOrders();
}

// LOGOUT
function logout(){
    location.reload();
}
