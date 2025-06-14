<?php

namespace Database\Seeders;

use App\Models\HealthProfile;
// use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class HealthProfileSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        HealthProfile::factory()->create(['user_id' => 1]); // Assuming user with ID 1 exists

        // Or you can loop through users and create health profiles for each user
        // foreach (User::all() as $user) {
        //     HealthProfile::factory()
        //         ->create(['user_id' => $user->id]);
        // }
        // If you want to create health profiles for all users, uncomment the above loop
    }
}
