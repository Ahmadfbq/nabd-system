<?php

namespace Database\Seeders;

use App\Models\Measurement;
// use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class MeasurementSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        Measurement::factory(50)
            ->create([
                'user_id' => 1, // Assuming user with ID 1 exists
            ]);
        // Or you can loop through users and create measurements for each
        // foreach (User::all() as $user) {
        //     Measurement::factory(10)
        //         ->create(['user_id' => $user->id]);
        // }
        // If you want to create measurements for all users, uncomment the above loop
    }
}
